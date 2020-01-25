package simple.engine.engine.gui.components;

import simple.engine.engine.Engine;
import simple.engine.engine.util.Counter;

import java.awt.Rectangle;
import java.awt.*;

public abstract class Widget {

    protected final Rectangle rectangle;

    public Widget(int x, int y, int width, int height) {
        rectangle = new Rectangle(x, y, width, height);
    }

    public abstract void paint(Graphics2D g);

    public void addAnimation(MOVEMENT movement, int distance, int stepSize) {
        int steps = distance / stepSize;
        switch (movement) {
            case HORIZONTAL:
                Counter h = new Counter(0, distance, stepSize);
                Engine.timingModule.scheduleMultiple(() -> rectangle.x += h.next(), 0, 50, steps);
                break;
            case VERTICAL:
                Counter v = new Counter(0, distance, stepSize);
                Engine.timingModule.scheduleMultiple(() -> rectangle.y += v.next(), 0, 50, steps);
                break;
        }
    }

    public void debug(Graphics2D g) {
        g.setColor(Color.CYAN);
        g.draw(rectangle);
    }

    public enum MOVEMENT {HORIZONTAL, VERTICAL}
}
