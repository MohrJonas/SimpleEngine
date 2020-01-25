package simple.engine.engine.gui.components;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageFrame extends StaticWidget {


    private final BufferedImage image;
    private final Color borderColor;
    private final int borderWidth;

    public ImageFrame(int x, int y, BufferedImage image, Color borderColor, int borderWidth) {
        super(x, y, image.getWidth() + borderWidth * 2, image.getHeight() + borderWidth * 2);
        this.image = image;
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(borderColor);
        g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g.drawImage(image, rectangle.x + borderWidth, rectangle.y + borderWidth, null);
    }
}
