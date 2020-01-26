package simple.engine.gui.components;

import simple.engine.util.GuiUtils;

import java.awt.*;

public class TextButton extends Button {

    private String text;

    public TextButton(int x, int y, String text, Runnable runnable) {
        super(x, y, GuiUtils.getStringWidth(text), GuiUtils.getStringHeight(text), runnable);
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
        rectangle.width = GuiUtils.getStringWidth(text);
        rectangle.height = GuiUtils.getStringHeight(text);
    }

    @Override
    public void paint(Graphics2D g) {
        g.drawString(text, rectangle.x, rectangle.y + GuiUtils.getStringHeight(text));
    }
}
