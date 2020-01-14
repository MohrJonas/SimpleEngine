package simple.engine.engine.gui;

import simple.engine.engine.gui.components.Button;
import simple.engine.engine.gui.components.Widget;

import java.awt.*;
import java.util.HashMap;

public class WidgetPack {

    private final HashMap<Integer, Widget> widgets = new HashMap<>();
    public boolean debug;

    public WidgetPack(Integer[] ids, Widget[] widgets) {
        if (ids.length != widgets.length)
            throw new IllegalArgumentException("Amount of IDs and Widgets must be the same");
        for (int i = 0; i < ids.length; i++) this.widgets.put(ids[i], widgets[i]);
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
            if (widget instanceof Button) {
                if (((Button) widget).onClick(x, y)) return;
            }
        }
    }
}
