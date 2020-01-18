package simple.engine.engine.gui.components;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageButton extends Button {

    private final BufferedImage image;

    public ImageButton(int x, int y, BufferedImage image, Runnable runnable) {
        super(x, y, image.getWidth(), image.getHeight(), runnable);
        this.image = image;
    }

    @Override
    public void paint(Graphics2D g) {
        g.drawImage(image, rectangle.x, rectangle.y, rectangle.width, rectangle.height, null);
    }
}
