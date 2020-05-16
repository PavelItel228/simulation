package entity.landscape;

import entity.enums.landscape.GrassHeight;
import entity.enums.landscape.GroundHeight;
import entity.enums.landscape.WaterHeight;

public class Ground {
    private final GroundHeight groundHeight;


    public Ground(GroundHeight groundHeight) {
        this.groundHeight = groundHeight;
    }

    public GroundHeight getGroundHeight() {
        return groundHeight;
    }
}
