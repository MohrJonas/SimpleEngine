package simple.engine.graphics;

import org.javatuples.Triplet;
import simple.engine.util.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class ImageComposition {

    private final LinkedList<Triplet<BufferedImage, Point, Integer>> images = new LinkedList<>();

    public ImageComposition(BufferedImage[] images, Point[] points, Integer[] layers) {
        if (images.length != points.length || images.length != layers.length)
            throw new IllegalArgumentException("All arrays must be of equal size");
        for (int i = 0; i < images.length; i++) {
            this.images.add(new Triplet<>(images[i], points[i], layers[i]));
        }
        Utils.sortTuple(this.images, 2);
    }

    public void paint(Graphics2D g) {
        images.forEach(t -> g.drawImage(t.getValue0(), t.getValue1().x, t.getValue1().y, null));
    }

    public void translate(int x, int y) {
        for (int i = 0; i < images.size(); i++) {
            images.set(i, images.get(i).setAt1(new Point(images.get(i).getValue1().x += x, images.get(i).getValue1().y += y)));
        }
    }

}
