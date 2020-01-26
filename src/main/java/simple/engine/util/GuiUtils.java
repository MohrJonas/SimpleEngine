package simple.engine.util;

import simple.engine.Engine;
import simple.engine.gui.components.Widget;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

public class GuiUtils {

    public static final Font FONT = new Font(Font.MONOSPACED, Font.PLAIN, 12);
    private static final AffineTransform AFFINE_TRANSFORM = new AffineTransform();
    private static final FontRenderContext RENDER_CONTEXT = new FontRenderContext(AFFINE_TRANSFORM, true, true);

    public static int getStringWidth(String s) {
        return (int) (FONT.getStringBounds(s, RENDER_CONTEXT).getWidth());
    }

    public static int getStringHeight(String s) {
        return (int) (FONT.getStringBounds(s, RENDER_CONTEXT).getHeight());
    }

    public static BufferedImage createImage(int width, int height, Color color) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setColor(color);
        g.fillRect(0, 0, width, height);
        return image;
    }

    public static Color randomColor() {
        return new Color(ThreadLocalRandom.current().nextInt(0, 256), ThreadLocalRandom.current().nextInt(0, 256), ThreadLocalRandom.current().nextInt(0, 256));
    }

    public static <T extends Widget> T snapLeft(T widget) {
        widget.rectangle.x = 0;
        return widget;
    }

    public static <T extends Widget> T snapRight(T widget) {
        widget.rectangle.x = Engine.getConfig().getWidth() - widget.rectangle.width;
        return widget;
    }

    public static <T extends Widget> T snapTop(T widget) {
        widget.rectangle.y = 0;
        return widget;
    }

    public static <T extends Widget> T snapBottom(T widget) {
        widget.rectangle.y = Engine.getConfig().getHeight() - widget.rectangle.height;
        return widget;
    }

    public static <T extends Widget> T centerHorizontally(T widget) {
        widget.rectangle.x = Engine.getConfig().getWidth() / 2 - widget.rectangle.width / 2;
        return widget;
    }

    public static <T extends Widget> T centerVertically(T widget) {
        widget.rectangle.y = Engine.getConfig().getHeight() / 2 - widget.rectangle.height / 2;
        return widget;
    }
}
