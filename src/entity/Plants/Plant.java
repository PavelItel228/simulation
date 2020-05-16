package entity.Plants;

public class Plant {
    public final static long PLANT_TYPE_MASK          = 0x0000_0200_0000_0000L;
    public final static int PLANT_TYPE_SHIFT          = 41;
    public final static int PLANT_TYPE_EMPTY           = 0x0;
    public final static int PLANT_TYPE_APPLE           = 0x1;
    public final static long ACTIVE_FLAG_PLANT_MASK   = 0x8000_0000_0000_0000L;
    public final static int ACTIVE_FLAG_PLANT_SHIFT   = 63;
}
