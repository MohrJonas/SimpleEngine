package simple.engine;

import java.awt.image.BufferedImage;

public class Tile {

    private final String image;
    private final String name;
    private final float breakLevel;
    private final float breakTime;

    public Tile(TilePrefab prefab) {
        this(prefab.image, prefab.name, prefab.breakLevel, prefab.breakTime);
    }

    public Tile(String image, String name, float breakLevel, float breakTime) {
        this.image = image;
        this.name = name;
        this.breakLevel = breakLevel;
        this.breakTime = breakTime;
    }

}
