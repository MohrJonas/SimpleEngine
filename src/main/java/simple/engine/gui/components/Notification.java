package simple.engine.gui.components;

import simple.engine.Engine;
import simple.engine.util.GuiUtils;

import java.awt.*;

public class Notification extends StaticWidget {

    private boolean visible;
    private String text;

    public Notification(int x, int y) {
        super(x, y, 0, 0);
    }

    public void show(String text, int delay) {
        visible = true;
        this.text = text;
        rectangle.width = GuiUtils.getStringWidth(text);
        rectangle.height = GuiUtils.getStringHeight(text);
        Engine.timingModule.schedule(() -> visible = false, delay);
    }

    @Override
    public void paint(Graphics2D g) {
        if (visible) g.drawString(text, rectangle.x, rectangle.y);
    }
}
