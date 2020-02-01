package simple.engine.tiles;

import java.util.Arrays;
import java.util.stream.Stream;

public class TileMap {

    private final int width;
    private final int height;
    private final Tile[][] tiles;


    public TileMap(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[height][width];
    }

    public Tile getTile(int x, int y) {
        return tiles[y][x];
    }

    public void setTile(Tile tile, int x, int y) {
        tiles[y][x] = tile;
    }

    public Stream<Tile[]> stream() {
        return Arrays.stream(tiles);
    }

    public Tile[][] getSubMap(int startX, int startY, int width, int height) {
        Tile[][] tiles = new Tile[width][height];
        for (int y = startY; y < startY + height; y++) {
            for (int x = startX; x < startX + width; x++) {
                tiles[y][x] = this.tiles[y][x];
            }
        }
        return tiles;
    }

    public CollisionMap createCollisionMap(int tileWidth, int tileHeight) {
        CollisionMap map = new CollisionMap();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (tiles[x][y].isPassable()) map.addCollision(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
            }
        }
        return map;
    }

}
