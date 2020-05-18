package entity.humans;

import dao.ChildrenDao;
import dao.DAOFactory;
import x.gui.main.XMainPanel;
import x.gui.map.XMapPanel;
import x.logic.force.XForce;
import x.logic.random.XRandom;

public class Woman{
    public final static int HUMAN_TYPE_WOMAN = 0x2;
    public final static int HUMAN_TYPE_COLOR_WOMAN = 0xB82C8F;
    public final static long WOMAN_PREGNANCY_MASK = 0x0000_01FF_0000_0000L;
    public final static int WOMAN_PREGNANCY_SHIFT = 32;
    private static int PREGNANCY_CHANCE = 30;
    private static int BORN_CHANCE = 50;
    private final ChildrenDao childrenDao = DAOFactory.getInstance().getChildrenDao();


    public boolean tryToGiveBirth(long cellData, int y, int x, int date) {
        XMapPanel map = XMainPanel.mapPanel;
        int pregnacy = map.getHumanPregnancyAt(y, x);
        if (pregnacy != 0 && pregnacy < 300) {
            map.setHumanPregnancyAt(map.getHumanPregnancyAt(y, x) + 1, y, x);
        }
        else if (pregnacy == 300) {
            map.setHumanPregnancyAt(0, y, x);
            map.setHumanEnergyAt(map.getHumanEnergyAt(y, x) - 4, y, x);
            map.setHumanSatietyAt(map.getHumanSatietyAt(y, x) - 4, y, x);
            map.setHumanAgeAt(map.getHumanAgeAt(y, x) + 1, y, x);
            map.setActiveFlagHumanAt(0, y, x);
            for (int yShift = -1; yShift < 2; yShift++) {
                for (int xShift = -1; xShift < 2; xShift++) {
                    int yTarget = y + yShift;
                    int xTarget = x + xShift;
                    if (XForce.isCellInMapRange(yTarget, xTarget) && map.getHumanTypeAt(yTarget, xTarget) == Human.HUMAN_TYPE_EMPTY) {
                        map.setHumanTypeAt(XRandom.generateBoolean(Woman.getBornChance()) ? Woman.HUMAN_TYPE_WOMAN : Man.HUMAN_TYPE_MAN, yTarget, xTarget);
                        map.setHumanAgeAt(301, yTarget, xTarget);
                        map.setHumanEnergyAt(63, yTarget, xTarget);
                        map.setHumanSatietyAt(63, yTarget, xTarget);
                        map.setHumanPregnancyAt(0, yTarget, xTarget);
                        map.setActiveFlagHumanAt(0, yTarget, xTarget);
                        childrenDao.create(new Children());
                        XMainPanel.eventsInfoPanel.update(date, "Child was born.");
                        return true;
                    }
                }
            }
            map.setHumanPregnancyAt(0, y, x);
            childrenDao.createDead(new Children());
            XMainPanel.eventsInfoPanel.update(date, "Child died.");
            return true;
        }
        return false;
    }

    private boolean isPregnant;

    public static int getPregnancyChance() {
        return PREGNANCY_CHANCE;
    }

    public static void setPregnancyChance(int pregnancyChance) {
        PREGNANCY_CHANCE = pregnancyChance;
    }

    public boolean isPregnant() {
        return isPregnant;
    }

    public void setPregnant(boolean pregnant) {
        isPregnant = pregnant;
    }

    public static int getBornChance() {
        return BORN_CHANCE;
    }

    public static void setBornChance(int bornChance) {
        BORN_CHANCE = bornChance;
    }
}
