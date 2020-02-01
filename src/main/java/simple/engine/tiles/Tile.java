package simple.engine.tiles;

import java.awt.image.BufferedImage;

public abstract class Tile {

    public abstract BufferedImage getImage();

    public abstract boolean isPassable();

    public abstract boolean isSelectable();

}
