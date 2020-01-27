package simple.engine.gui.layouts;

import simple.engine.gui.components.Widget;

import java.util.concurrent.atomic.AtomicInteger;

public class HorizontalLayoutManager extends LayoutManager {

    private final int padding;
    private final LAYOUT_DIRECTION direction;

    public HorizontalLayoutManager(int padding, LAYOUT_DIRECTION direction) {
        this.padding = padding;
        this.direction = direction;
    }

    @Override
    public Widget[] layout(Widget... widgets) {
        AtomicInteger currentX = new AtomicInteger(widgets[0].rectangle.x + widgets[0].rectangle.width + padding);
        final int y = widgets[0].rectangle.y;
        for (int i = 1; i < widgets.length; i++) {
            widgets[i].rectangle.y = y;
            widgets[i].rectangle.x = currentX.get();
            switch (direction) {
                case LEFT:
                    currentX.addAndGet(-(widgets[i].rectangle.width + padding));
                    break;
                case RIGHT:
                    currentX.addAndGet(widgets[i].rectangle.width + padding);
                    break;
            }
        }
        return widgets;
    }

    public enum LAYOUT_DIRECTION {LEFT, RIGHT}
}
