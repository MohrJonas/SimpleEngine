package simple.engine.engine;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageOptimizer {

    public static BufferedImage optimize(BufferedImage image) {
        GraphicsConfiguration gfxConfig = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        if (image.getColorModel().equals(gfxConfig.getColorModel())) return image;
        BufferedImage newImage = gfxConfig.createCompatibleImage(image.getWidth(), image.getHeight(), image.getTransparency());
        Graphics2D g2d = newImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return newImage;
    }

}
