package entity.humans;

public class Woman extends Human{
    public final static int HUMAN_TYPE_WOMAN = 0x2;
    public final static int HUMAN_TYPE_COLOR_WOMAN = 0xB82C8F;
    public final static long WOMAN_PREGNANCY_MASK = 0x0000_01FF_0000_0000L;
    public final static int WOMAN_PREGNANCY_SHIFT = 32;
    private static int PREGNANCY_CHANCE = 30;
    private static int BORN_CHANCE = 50;

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
