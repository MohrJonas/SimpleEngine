package simple.engine.gui.layouts;

import simple.engine.gui.components.Widget;

import java.util.concurrent.atomic.AtomicInteger;

public class VerticalLayoutManager extends LayoutManager {

    private final int padding;
    private final LAYOUT_DIRECTION direction;

    public VerticalLayoutManager(int padding, LAYOUT_DIRECTION direction) {
        this.padding = padding;
        this.direction = direction;
    }

    @Override
    public Widget[] layout(Widget... widgets) {
        AtomicInteger currentY = new AtomicInteger(widgets[0].rectangle.y + widgets[0].rectangle.width + padding);
        final int x = widgets[0].rectangle.x;
        for (int i = 1; i < widgets.length; i++) {
            widgets[i].rectangle.x = x;
            widgets[i].rectangle.y = currentY.get();
            switch (direction) {
                case TOP:
                    currentY.addAndGet(-(widgets[i].rectangle.width + padding));
                    break;
                case BOTTOM:
                    currentY.addAndGet(widgets[i].rectangle.width + padding);
                    break;
            }
        }
        return widgets;
    }

    public enum LAYOUT_DIRECTION {TOP, BOTTOM}
}
