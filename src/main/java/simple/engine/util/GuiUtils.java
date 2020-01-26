package simple.engine.util;

import simple.engine.Engine;
import simple.engine.gui.components.Widget;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

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

    @SafeVarargs
    public static <T extends Widget> T[] alignHorizontally(int padding, T... widgets) {
        AtomicInteger currentX = new AtomicInteger(widgets[0].rectangle.x + widgets[0].rectangle.width + padding);
        System.out.println(currentX.get());
        final int y = widgets[0].rectangle.y;
        for (int i = 1; i < widgets.length; i++) {
            widgets[i].rectangle.x = currentX.get();
            widgets[i].rectangle.y = y;
            currentX.addAndGet(widgets[i].rectangle.width + padding);
        }
        return widgets;
    }

    @SafeVarargs
    public static <T extends Widget> T[] alignVertically(int padding, T... widgets) {
        AtomicInteger currentY = new AtomicInteger(widgets[0].rectangle.y + widgets[0].rectangle.width + padding);
        final int x = widgets[0].rectangle.x;
        for (int i = 1; i < widgets.length; i++) {
            widgets[i].rectangle.y = currentY.get();
            widgets[i].rectangle.x = x;
            currentY.addAndGet(widgets[i].rectangle.height + padding);
        }
        return widgets;
    }

    public static Rectangle getBoundingBox(Widget... widgets) {
        AtomicInteger x = new AtomicInteger(Integer.MAX_VALUE);
        AtomicInteger y = new AtomicInteger(Integer.MAX_VALUE);
        AtomicInteger w = new AtomicInteger(Integer.MIN_VALUE);
        AtomicInteger h = new AtomicInteger(Integer.MIN_VALUE);
        Arrays.stream(widgets).forEach(widget -> {
            if (widget.rectangle.x < x.get()) x.set(widget.rectangle.x);
            if (widget.rectangle.y < y.get()) y.set(widget.rectangle.y);
            if (widget.rectangle.width > w.get()) w.set(widget.rectangle.width);
            if (widget.rectangle.height > w.get()) w.set(widget.rectangle.height);
        });
        return new Rectangle(x.get(), y.get(), w.get(), h.get());
    }

    public static <T extends Widget> T[] translate(int x, int y, T[] widgets) {
        Arrays.stream(widgets).forEach(widget -> {
            widget.rectangle.x += x;
            widget.rectangle.y += y;
        });
        return widgets;
    }
}
