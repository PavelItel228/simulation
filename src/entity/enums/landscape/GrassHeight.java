package entity.enums.landscape;
// перечисления высоты обьекта вместе с соответствующей кодировкой и кодом цвета для этой высоты
public enum GrassHeight {
    LOW(0x5, 0xB3D77E),
    HIGH(0x6, 0xA8C976);

    private final int typeCode;
    private final int colorCode;

    GrassHeight(int typeCode , int colorCode) {
        this.typeCode = typeCode;
        this.colorCode = colorCode;
    }

    GrassHeight() {
        this(0, 0);
    }

    public final int getTypeCode() {
        return typeCode;
    }

    public final int getColorCode() {
        return colorCode;
    }
}
