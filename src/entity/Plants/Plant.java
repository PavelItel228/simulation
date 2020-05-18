package entity.Plants;

import entity.enums.landscape.WaterHeight;
import entity.humans.Human;
import entity.landscape.Water;
import x.gui.main.XMainPanel;
import x.gui.map.XMapPanel;
import x.logic.random.XRandom;

import static x.logic.force.XForce.isCellInMapRange;

public class Plant {
    public final static long PLANT_TYPE_MASK          = 0x0000_0200_0000_0000L;
    public final static int PLANT_TYPE_SHIFT          = 41;
    public final static int PLANT_TYPE_EMPTY           = 0x0;
    public final static int PLANT_TYPE_APPLE           = 0x1;
    public final static long ACTIVE_FLAG_PLANT_MASK   = 0x8000_0000_0000_0000L;
    public final static int ACTIVE_FLAG_PLANT_SHIFT   = 63;
    private final Water highWater = new Water(WaterHeight.HIGH);
    private final Water lowWater = new Water(WaterHeight.LOW);

    // PLANT - MAKE FRUITS
    private static void tryToMakeFruits(long cellData, int y, int x, int date) {
        if (date % 30 == 0) {
            XMapPanel map = XMainPanel.mapPanel;
            map.setPlantFruitsAt(map.getPlantFruitsAt(y, x) + XRandom.generateInteger(5, 15), y, x);
        }
    }

    public void actPlant(long cellData, int y, int x, int date) {
        tryToMakeFruits(cellData, y, x, date);
        tryToDropFruit(cellData, y, x);
    }

    // PLANT - DROP FRUIT
    private void tryToDropFruit(long cellData, int y, int x) {
        XMapPanel map = XMainPanel.mapPanel;
        if (map.getPlantFruitsAt(y, x) == 0) {
            return;
        }
        // 0.01 * 0.50 = 0.005
        boolean decision = XRandom.generateBoolean(FruitPlant.getFruitDropChance()) && XRandom.generateBoolean(50);
        if (decision) {
            map.setPlantFruitsAt(map.getPlantFruitsAt(y, x) - 1, y, x);
            int yTarget = y + XRandom.generateInteger(-2, 2);
            int xTarget = x + XRandom.generateInteger(-2, 2);
            if (isCellInMapRange(yTarget, xTarget) && (yTarget != y || xTarget != x)) {
                int landscapeTarget = map.getLandscapeTypeAt(yTarget, xTarget);
                int humanTarget = map.getHumanTypeAt(yTarget, xTarget);
                int plantTarget = map.getPlantTypeAt(yTarget, xTarget);
                if (landscapeTarget != lowWater.getWaterHeight().getTypeCode()
                        && landscapeTarget != highWater.getWaterHeight().getTypeCode()
                        && humanTarget == Human.HUMAN_TYPE_EMPTY
                        && plantTarget == Plant.PLANT_TYPE_EMPTY) {
                    map.setPlantTypeAt(Plant.PLANT_TYPE_APPLE, yTarget, xTarget);
                }
            }
        }
    }
}
