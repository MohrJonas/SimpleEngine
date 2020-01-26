package simple.engine.gui.components;

import simple.engine.util.GuiUtils;

import java.awt.*;

public class Label extends DynamicWidget<String> {

    private String text;

    public Label(int x, int y, String text) {
        super(x, y, GuiUtils.getStringWidth(text), GuiUtils.getStringHeight(text));
        this.text = text;
    }

    @Override
    public void update(String s) {
        text = s;
        rectangle.width = GuiUtils.getStringWidth(text);
        rectangle.height = GuiUtils.getStringHeight(text);
    }

    @Override
    public void paint(Graphics2D g) {
        g.drawString(text, rectangle.x, rectangle.y + GuiUtils.getStringHeight(text));
    }
}
