package entity.enums.landscape;
// перечисления высоты обьекта вместе с соответствующей кодировкой и кодом цвета для этой высоты

public enum  GroundHeight {
    LOW(0x3, 0xDDB985),
    HIGH(0x4, 0xD1AF7D);

    private final int typeCode;
    private final int colorCode;

    GroundHeight(int typeCode , int colorCode) {
        this.typeCode = typeCode;
        this.colorCode = colorCode;
    }

    GroundHeight() {
        this(0, 0);
    }

    public final int getTypeCode() {
        return typeCode;
    }

    public final int getColorCode() {
        return colorCode;
    }
}
