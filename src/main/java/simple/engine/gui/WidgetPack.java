package simple.engine.gui;

import simple.engine.gui.components.Button;
import simple.engine.gui.components.Widget;

import java.awt.*;
import java.util.HashMap;

public class WidgetPack {

    private final HashMap<Integer, Widget> widgets = new HashMap<>();
    public boolean debug;

    public WidgetPack(Widget... widgets) {
        for (int i = 0; i < widgets.length; i++) this.widgets.put(i, widgets[i]);
    }

    public <T extends Widget> T get(int id, Class<T> t) {
        if (!widgets.containsKey(id))
            throw new IllegalArgumentException("Widget with ID ".concat(String.valueOf(id)).concat(" doesn't exist"));
        return t.cast(widgets.get(id));
    }

    public Widget get(int id) {
        if (!widgets.containsKey(id))
            throw new IllegalArgumentException("Widget with ID ".concat(String.valueOf(id)).concat(" doesn't exist"));
        return widgets.get(id);
    }

    public void paint(Graphics2D g) {
        widgets.values().forEach(widget -> widget.paint(g));
    }

    public void debug(Graphics2D g) {
        widgets.values().forEach(widget -> widget.debug(g));
    }

    public void onClick(int x, int y) {
        for (Widget widget : widgets.values()) {
            if (widget instanceof simple.engine.gui.components.Button) {
                if (((Button) widget).onClick(x, y)) return;
            }
        }
    }
}