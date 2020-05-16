package entity.enums.landscape;

// перечисления высоты обьекта вместе с соответствующей кодировкой и кодом цвета для этой высоты

public enum WaterHeight {
    LOW(0x1, 0x60A4B1),
    HIGH(0x2, 0x6CB8C6);

    private final int typeCode;
    private final int colorCode;

    WaterHeight(int typeCode , int colorCode) {
        this.typeCode = typeCode;
        this.colorCode = colorCode;
    }

    WaterHeight() {
        this(0, 0);
    }

    public final int getTypeCode() {
        return typeCode;
    }

    public final int getColorCode() {
        return colorCode;
    }
}
