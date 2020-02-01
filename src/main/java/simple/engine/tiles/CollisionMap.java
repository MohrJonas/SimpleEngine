package simple.engine.tiles;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class CollisionMap {

    private final ArrayList<Rectangle2D> collisions = new ArrayList<>();

    public void addCollision(int x, int y, int width, int height) {
        collisions.add(new Rectangle2D.Float(x, y, width, height));
    }

    public boolean collides(int x, int y, int width, int height) {
        for (Rectangle2D collision : collisions) {
            if (collision.intersects(new Rectangle2D.Float(x, y, width, height))) return true;
        }
        return false;
    }

    public void debug(Graphics2D g) {
        g.setColor(Color.RED);
        g.setStroke(new BasicStroke(4));
        collisions.forEach(g::draw);
    }

}
