package entity.Plants;

public class FruitPlant extends Plant{
    public final static long PLANT_FRUITS_MASK = 0x0000_FC00_0000_0000L;
    public final static int PLANT_FRUITS_SHIFT = 42;
    public static int FRUIT_DROP_CHANCE = 1;

    public static int getFruitDropChance() {
        return FRUIT_DROP_CHANCE;
    }

    public static void setFruitDropChance(int fruitDropChance) {
        FRUIT_DROP_CHANCE = fruitDropChance;
    }
}
