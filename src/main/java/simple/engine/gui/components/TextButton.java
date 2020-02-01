package simple.engine.gui.components;

import simple.engine.util.GuiUtils;

import java.awt.*;

public class TextButton extends Button {

    private String text;
    private boolean hasMargin;

    public TextButton(int x, int y, String text, Runnable runnable) {
        super(x, y, GuiUtils.getStringWidth(text), GuiUtils.getStringHeight(text), runnable);
        this.text = text;
        hasMargin = false;
    }

    public TextButton(int x, int y, int width, int height, String text, Runnable runnable) {
        super(x, y, width, height, runnable);
        this.text = text;
        hasMargin = true;
    }

    public void setText(String text) {
        this.text = text;
        if (!hasMargin) {
            rectangle.width = GuiUtils.getStringWidth(text);
            rectangle.height = GuiUtils.getStringHeight(text);
        }
    }

    @Override
    public void paint(Graphics2D g) {
        if (!hasMargin)
            g.drawString(text, rectangle.x, rectangle.y + GuiUtils.getStringHeight(text));
        else {
            g.setColor(Color.BLACK);
            g.setStroke(new BasicStroke(4));
            g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            g.drawString(text, rectangle.x + (rectangle.width - GuiUtils.getStringWidth(text)) / 2, rectangle.y + GuiUtils.getStringHeight(text) + (rectangle.height - GuiUtils.getStringHeight(text)) / 2);
        }
    }
}
