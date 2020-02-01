package simple.engine.gui.components;

import simple.engine.util.GuiUtils;

import java.awt.*;

public class Label extends DynamicWidget<String> {

    private final Font font;
    private String text;

    public Label(int x, int y, String text) {
        super(x, y, GuiUtils.getStringWidth(text), GuiUtils.getStringHeight(text));
        this.text = text;
        font = null;
    }

    public Label(int x, int y, String text, Font font) {
        super(x, y, GuiUtils.getStringWidth(text, font), GuiUtils.getStringHeight(text, font));
        this.text = text;
        this.font = font;
    }

    @Override
    public void update(String s) {
        text = s;
        rectangle.width = font != null ? GuiUtils.getStringWidth(text, font) : GuiUtils.getStringWidth(text);
        rectangle.height = font != null ? GuiUtils.getStringHeight(text, font) : GuiUtils.getStringHeight(text);
    }

    @Override
    public void paint(Graphics2D g) {
        if (font != null) g.setFont(font);
        g.drawString(text, rectangle.x, rectangle.y + GuiUtils.getStringHeight(text));
    }
}
