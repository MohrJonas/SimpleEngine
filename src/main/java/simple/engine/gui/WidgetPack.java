package simple.engine.gui;

import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import simple.engine.gui.components.Button;
import simple.engine.gui.components.Widget;
import simple.engine.gui.layouts.LayoutManager;

import java.awt.*;
import java.util.Arrays;

public class WidgetPack {

    private final DualHashBidiMap<Integer, Widget> widgets = new DualHashBidiMap<>();
    public boolean debug;

    public WidgetPack(Widget... widgets) {
        for (int i = 0; i < widgets.length; i++) this.widgets.put(i, widgets[i]);
    }

    public <T extends Widget> T get(int id, Class<T> t) {
        if (!widgets.containsKey(id))
            throw new IllegalArgumentException("Widget with ID ".concat(String.valueOf(id)).concat(" doesn't exist"));
        return t.cast(widgets.get(id));
    }

    public void paint(Graphics2D g) {
        widgets.values().forEach(widget -> widget.paint(g));
    }

    public void debug(Graphics2D g) {
        widgets.values().forEach(widget -> widget.debug(g));
    }

    public void layout(LayoutManager manager) {
        DualHashBidiMap<Integer, Widget> map = new DualHashBidiMap<>();
        Arrays.stream(manager.layout(widgets.values().toArray(new Widget[]{}))).forEach(widget -> {
            map.put(widgets.getKey(widget), widget);
        });
        widgets.clear();
        widgets.putAll(map);
    }

    public void onClick(int x, int y) {
        for (Widget widget : widgets.values()) {
            if (widget instanceof Button) {
                if (((Button) widget).onClick(x, y)) return;
            }
        }
    }
}
