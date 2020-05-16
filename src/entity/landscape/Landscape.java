package entity.landscape;

public class Landscape {
    public static final long LANDSCAPE_TYPE_MASK = 0x0000_0000_0000_0007L;
    public static final int LANDSCAPE_TYPE_SHIFT = 0;
    public static final int LANDSCAPE_TYPE_EMPTY = 0x0;
    public static final int CELLS       = 4225;
    public static final int CELLS_WATER = 1713;
    public static final int CELLS_LAND  = 2512;
}
