package simple.engine.engine.util;

import org.imgscalr.Scalr;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScalingUtils {

    public static Rectangle scale(Rectangle r, int expectedWidth, int expectedHeight, int actualWidth, int actualHeight) {
        int relativeWidth = expectedWidth / r.width;
        int relativeHeight = expectedHeight / r.height;
        r.width = relativeWidth * actualWidth;
        r.height = relativeHeight * actualHeight;
        return r;
    }

    public static BufferedImage scale(BufferedImage image, int expectedWidth, int expectedHeight, int actualWidth, int actualHeight) {
        int relativeWidth = expectedWidth / image.getWidth();
        int relativeHeight = expectedHeight / image.getHeight();
        return Scalr.resize(image, Scalr.Mode.AUTOMATIC, relativeWidth * actualWidth, relativeHeight * actualHeight);
    }

}
