package entity.humans;

import dao.ChildrenDao;
import dao.DAOFactory;
import dao.HumanDao;
import entity.BaseEntity;
import entity.enums.HumanStatus;
import entity.enums.landscape.WaterHeight;
import entity.landscape.Water;
import x.data.ucf.XUcfCoder;
import x.data.ucf.XUcfCoderFactory;
import x.gui.info.events.XEventsInfoPanel;
import x.gui.main.XMainPanel;
import x.gui.map.XMapPanel;
import x.logic.random.XRandom;
import x.logic.statistic.XStatistic;
import x.logic.statistic.XStatisticFactory;

import static x.logic.force.XForce.isCellInMapRange;

public class Human extends BaseEntity {
    private  Integer age;
    private  HumanStatus status;
    public static int lifeDuration = 30;
    public final static long HUMAN_TYPE_MASK = 0x0000_0000_0000_0018L;
    public final static int HUMAN_TYPE_SHIFT = 3;
    public final static int HUMAN_TYPE_EMPTY = 0x0;
    public final static long HUMAN_AGE_MASK = 0x0000_0000_000F_FFE0L;
    public final static int HUMAN_AGE_SHIFT = 5;
    public final static long HUMAN_ENERGY_MASK = 0x0000_0000_03F0_0000L;
    public final static int HUMAN_ENERGY_SHIFT = 20;
    public final static long HUMAN_SATIETY_MASK = 0x0000_0000_FC00_0000L;
    public final static int HUMAN_SATIETY_SHIFT = 26;
    public final static long ACTIVE_FLAG_HUMAN_MASK = 0x4000_0000_0000_0000L;
    public final static int ACTIVE_FLAG_HUMAN_SHIFT = 62;

    private final XUcfCoder xUcfCoder = XUcfCoderFactory.getInstance();
    private final Water highWater = new Water(WaterHeight.HIGH);
    private final Water lowWater = new Water(WaterHeight.LOW);
    private final XStatistic xStatistic = XStatisticFactory.getInstance();
    private final HumanDao humanDao = DAOFactory.getInstance().getHumanDao();
    private final ChildrenDao childrenDao = DAOFactory.getInstance().getChildrenDao();
    private final Woman woman = new Woman();
    private final Man man = new Man();

    public Human(Integer age, HumanStatus status) {
        this.age = age;
        this.status = status;
    }

    public Human() {
    }

    public boolean tryToDie(long cellData, int y, int x, int date) {
        XEventsInfoPanel events = XMainPanel.eventsInfoPanel;
        int energy = xUcfCoder.decodeHumanEnergy(cellData);
        if (energy == 0) {
            clearHuman(y, x);
            humanDao.createDiedByEnergy(new Human());
            events.update(date, "Human died by [Low Energy].");
            return true;
        }
        int satiety = xUcfCoder.decodeHumanSatiety(cellData);
        if (satiety == 0) {
            clearHuman(y, x);
            humanDao.createDiedBySatiety(new Human());
            events.update(date, "Human died [Low Satiety].");
            return true;
        }
        // f(x): f(0..9000) <= 0, f(24000..32767) >= 100;
        // f(x) = 2*(x/300) - 60;
        // Every year + 2% (since 30 years)
        boolean decision = XRandom.generateBoolean(2 * (xUcfCoder.decodeHumanAge(cellData) / 300) - Human.getLifeDuration() * 2);
        if (decision) {
            clearHuman(y, x);
            humanDao.createDiedByAge(new Human());
            events.update(date, "Human died [Age].");
            return true;
        }
        return false;
    }

    public void actHuman(long cellData, int y, int x, int date) {
        if (tryToDie(cellData, y, x, date)
                || woman.tryToGiveBirth(cellData, y, x, date)
                || tryToSleep(cellData, y, x)
                || tryToEat(cellData, y, x, date)
                || man.tryToMakeChild(cellData, y, x, date)
                || tryToMove(cellData, y, x, 0, 0, date)) {/*Do nothing*/}
    }

    private boolean tryToSleep(long cellData, int y, int x) {
        // f(x): f(0..10) >= 100, f(60..64) <= 0;
        // f(x) = 120 - 2*x;
        boolean decision = XRandom.generateBoolean(120 - 2 * xUcfCoder.decodeHumanEnergy(cellData));
        if (decision) {
            XMapPanel map = XMainPanel.mapPanel;
            map.setHumanEnergyAt(63, y, x);
            map.setHumanSatietyAt(map.getHumanSatietyAt(y, x) - 1, y, x);
            map.setHumanAgeAt(map.getHumanAgeAt(y, x) + 1, y, x);
            map.setActiveFlagHumanAt(0, y, x);
            return true;
        }
        return false;
    }

    private boolean tryToMove(long cellData, int y, int x, int yShift, int xShift, int date) {
        XMapPanel map = XMainPanel.mapPanel;
        if (yShift == 0 && xShift == 0) {
            yShift = XRandom.generateInteger(-1, 1);
            xShift = XRandom.generateInteger(-1, 1);
        }
        if (yShift != 0 || xShift != 0) {
            int yTarget = y + yShift;
            int xTarget = x + xShift;
            if (!isCellInMapRange(yTarget, xTarget)) {
                clearHuman(y, x);
                humanDao.createDiedByLost(new Human());
                XMainPanel.eventsInfoPanel.update(date, "Human died [Lost].");
                return true;
            }
            if (map.getHumanTypeAt(yTarget, xTarget) == 0) {
                moveHuman(y, x, yTarget, xTarget);
                int landscapeTarget = map.getLandscapeTypeAt(yTarget, xTarget);
                if (landscapeTarget == lowWater.getWaterHeight().getTypeCode() || landscapeTarget == highWater.getWaterHeight().getTypeCode()) {
                    map.setHumanEnergyAt(map.getHumanEnergyAt(yTarget, xTarget) - 3, yTarget, xTarget);
                    map.setHumanSatietyAt(map.getHumanSatietyAt(yTarget, xTarget) - 3, yTarget, xTarget);
                } else {
                    map.setHumanEnergyAt(map.getHumanEnergyAt(yTarget, xTarget) - 2, yTarget, xTarget);
                    map.setHumanSatietyAt(map.getHumanSatietyAt(yTarget, xTarget) - 2, yTarget, xTarget);
                }
                map.setHumanAgeAt(map.getHumanAgeAt(yTarget, xTarget) + 1, yTarget, xTarget);
                map.setActiveFlagHumanAt(0, yTarget, xTarget);
                return true;
            }
        }
        map.setHumanEnergyAt(map.getHumanEnergyAt(y, x) - 1, y, x);
        map.setHumanSatietyAt(map.getHumanSatietyAt(y, x) - 1, y, x);
        map.setHumanAgeAt(map.getHumanAgeAt(y, x) + 1, y, x);
        map.setActiveFlagHumanAt(0, y, x);
        return false;
    }

    private boolean tryToEat(long cellData, int y, int x, int date) {
        XMapPanel map = XMainPanel.mapPanel;
        // f(x): f(0..10) >= 100, f(60..64) <= 0;
        // f(x) = 120 - 2*x;
        boolean decision = XRandom.generateBoolean(120 - 2 * xUcfCoder.decodeHumanSatiety(cellData));
        if (decision) {
            for (int yShift = -1; yShift < 2; yShift++) {
                for (int xShift = -1; xShift < 2; xShift++) {
                    int yTarget = y + yShift;
                    int xTarget = x + xShift;
                    if (isCellInMapRange(yTarget, xTarget)) {
                        int fruitsTarget = map.getPlantFruitsAt(yTarget, xTarget);
                        if (fruitsTarget != 0) {
                            map.setPlantFruitsAt(--fruitsTarget, yTarget, xTarget);
                            if (map.getHumanAgeAt(y, x) < 10) {
                                map.setHumanSatietyAt(map.getHumanSatietyAt(y, x) + 32, y, x);
                            } else {
                                map.setHumanSatietyAt(map.getHumanSatietyAt(y, x) + 16, y, x);
                            }
                            map.setHumanEnergyAt(map.getHumanEnergyAt(y, x) - 1, y, x);
                            map.setHumanAgeAt(map.getHumanAgeAt(y, x) + 1, y, x);
                            map.setActiveFlagHumanAt(0, y, x);
                            return true;
                        }
                    }
                }
            }
            int minTarget = XMapPanel.MAP_SIZE * XMapPanel.MAP_SIZE + XMapPanel.MAP_SIZE * XMapPanel.MAP_SIZE;
            int yTarget = y;
            int xTarget = x;
            for (int yTemp = 0; yTemp < XMapPanel.MAP_SIZE; yTemp++) {
                for (int xTemp = 0; xTemp < XMapPanel.MAP_SIZE; xTemp++) {
                    if (map.getPlantFruitsAt(yTemp, xTemp) != 0) {
                        int yDelta = Math.abs(yTemp - y);
                        int xDelta = Math.abs(xTemp - x);
                        int minTemp = yDelta * yDelta + xDelta * xDelta;
                        if (minTemp < minTarget) {
                            minTarget = minTemp;
                            yTarget = yTemp;
                            xTarget = xTemp;
                        }
                    }
                }
            }
            if (yTarget != y || xTarget != x) {
                int yShift = 0;
                int xShift = 0;
                int yDelta = yTarget - y;
                int xDelta = xTarget - x;
                if (yDelta < 0) {
                    yShift = -1;
                } else if (yDelta > 0) {
                    yShift = 1;
                }
                if (xDelta < 0) {
                    xShift = -1;
                } else if (xDelta > 0) {
                    xShift = 1;
                }
                return this.tryToMove(cellData, y, x, yShift, xShift, date);
            }
        }
        return false;
    }

    // Delete Human from map
    private static void clearHuman(int y, int x) {
        XMapPanel map = XMainPanel.mapPanel;
        map.setHumanTypeAt(0, y, x);
        map.setHumanAgeAt(0, y, x);
        map.setHumanEnergyAt(0, y, x);
        map.setHumanSatietyAt(0, y, x);
        map.setHumanPregnancyAt(0, y, x);
    }

    private static void moveHuman(int yFrom, int xFrom, int yTo, int xTo) {
        XMapPanel map = XMainPanel.mapPanel;
        map.setHumanTypeAt(map.getHumanTypeAt(yFrom, xFrom), yTo, xTo);
        map.setHumanAgeAt(map.getHumanAgeAt(yFrom, xFrom), yTo, xTo);
        map.setHumanEnergyAt(map.getHumanEnergyAt(yFrom, xFrom), yTo, xTo);
        map.setHumanSatietyAt(map.getHumanSatietyAt(yFrom, xFrom), yTo, xTo);
        map.setHumanPregnancyAt(map.getHumanPregnancyAt(yFrom, xFrom), yTo, xTo);
        clearHuman(yFrom, xFrom);
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public HumanStatus getStatus() {
        return status;
    }

    public void setStatus(HumanStatus status) {
        this.status = status;
    }

    public static void setLifeDuration(int lifeDuration) {
        Human.lifeDuration = lifeDuration;
    }

    public static int getLifeDuration() {
        return lifeDuration;
    }
}
