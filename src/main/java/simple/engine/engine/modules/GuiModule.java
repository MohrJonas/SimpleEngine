package simple.engine.engine.modules;

import simple.engine.engine.GameConfig;
import simple.engine.engine.gui.WidgetPack;

import java.awt.*;

public class GuiModule extends Module {

    private WidgetPack widgetPack;
    private boolean active;
    private boolean debug;

    public GuiModule(GameConfig config) {
        super(config);
    }

    public void onClick(int x, int y) {
        if (widgetPack != null && active)
            widgetPack.onClick(x, y);
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setWidgetPack(WidgetPack widgetPack) {
        this.widgetPack = widgetPack;
    }

    public void paint(Graphics2D g) {
        if (active && widgetPack != null) widgetPack.paint(g);
        if (debug && widgetPack != null) widgetPack.debug(g);
    }
}
