import simple.engine.Engine;
import simple.engine.util.GameConfig;
import simple.engine.gui.WidgetPack;
import simple.engine.gui.components.ImageFrame;
import simple.engine.gui.layouts.HorizontalLayoutManager;
import simple.engine.modules.FrameListener;
import simple.engine.modules.GraphicModule;
import simple.engine.util.GuiUtils;
import simple.engine.util.Logger;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Engine.initialize(new GameConfig());
        Logger.disableLevel("fps");
        WidgetPack wp = new WidgetPack(
                new ImageFrame(0, 0, GuiUtils.createImage(64, 64, GuiUtils.randomColor()), GuiUtils.randomColor(), 5),
                new ImageFrame(0, 0, GuiUtils.createImage(64, 64, GuiUtils.randomColor()), GuiUtils.randomColor(), 5),
                new ImageFrame(0, 0, GuiUtils.createImage(64, 64, GuiUtils.randomColor()), GuiUtils.randomColor(), 5),
                new ImageFrame(0, 0, GuiUtils.createImage(64, 64, GuiUtils.randomColor()), GuiUtils.randomColor(), 5),
                new ImageFrame(0, 0, GuiUtils.createImage(64, 64, GuiUtils.randomColor()), GuiUtils.randomColor(), 5),
                new ImageFrame(0, 0, GuiUtils.createImage(64, 64, GuiUtils.randomColor()), GuiUtils.randomColor(), 5),
                new ImageFrame(0, 0, GuiUtils.createImage(64, 64, GuiUtils.randomColor()), GuiUtils.randomColor(), 5)
        );
        wp.layout(new HorizontalLayoutManager(4, HorizontalLayoutManager.LAYOUT_DIRECTION.RIGHT));
        Engine.guiModule.setWidgetPack(wp);
        Engine.guiModule.setActive(true);
        Engine.graphicModule.addFrameListener(new FrameListener() {
            @Override
            public void onNextFrame(Graphics2D g) {
                Engine.guiModule.paint(g);
            }
        }, GraphicModule.LAST_LAYER);
    }
}