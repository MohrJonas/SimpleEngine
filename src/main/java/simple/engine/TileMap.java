package simple.engine;

import java.io.Serializable;

public class TileMap implements Serializable {

    private final Tile[][] tiles;
    private final int width;
    private final int height;

    public TileMap(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
    }

    public void set(Tile tile, int x, int y) {
        tiles[x][y] = tile;
    }

    public Tile get(int x, int y) {
        return tiles[x][y];
    }
}
