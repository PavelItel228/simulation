package entity.landscape;

import entity.enums.landscape.WaterHeight;

public class Water {
    private final WaterHeight waterHeight;


    public Water(WaterHeight waterHeight) {
        this.waterHeight = waterHeight;
    }

    public WaterHeight getWaterHeight() {
        return waterHeight;
    }

}
