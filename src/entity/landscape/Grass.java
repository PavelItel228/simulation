package entity.landscape;

import entity.enums.landscape.GrassHeight;
import entity.enums.landscape.WaterHeight;

public class Grass {
    private final GrassHeight grassHeight;


    public Grass(GrassHeight grassHeight) {
        this.grassHeight = grassHeight;
    }

    public GrassHeight getGrassHeight() {
        return grassHeight;
    }
}
