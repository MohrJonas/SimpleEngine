package simple.engine.engine.gui.components;

import simple.engine.engine.Engine;

import java.awt.Rectangle;
import java.awt.*;

public abstract class Widget {

    protected Rectangle rectangle;

    public Widget(int x, int y, int width, int height) {
        rectangle = new Rectangle(x, y, width, height);
    }

    public abstract void paint(Graphics2D g);

    public void addAnimation(MOVEMENT movement, int distance, int stepSize) {
        int steps = distance / stepSize;
        switch (movement) {
            case HORIZONTAL:
                Engine.timingModule.scheduleMultiple(() -> rectangle.x += stepSize, 0, 50, steps);
                break;
            case VERTICAL:
                Engine.timingModule.scheduleMultiple(() -> rectangle.y += stepSize, 0, 50, steps);
                break;
        }
    }

    public void debug(Graphics2D g) {
        g.setColor(Color.CYAN);
        g.draw(rectangle);
    }

    public enum MOVEMENT {HORIZONTAL, VERTICAL}
}
