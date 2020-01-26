import simple.engine.Engine;
import simple.engine.GameConfig;
import simple.engine.gui.WidgetPack;
import simple.engine.gui.components.ImageFrame;
import simple.engine.modules.FrameListener;
import simple.engine.modules.GraphicModule;
import simple.engine.util.GuiUtils;
import simple.engine.util.Logger;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Engine.initialize(new GameConfig());
        Logger.disableLevel("fps");
        int offsetY = Engine.getConfig().getHeight() - 40;
        int offsetX = 224;
        WidgetPack wp = new WidgetPack(
                GuiUtils.snapTop(GuiUtils.snapLeft(new ImageFrame(0, 0, GuiUtils.createImage(64, 64, GuiUtils.randomColor()), GuiUtils.randomColor(), 5))),
                GuiUtils.snapTop(GuiUtils.snapRight(new ImageFrame(0, 0, GuiUtils.createImage(64, 64, GuiUtils.randomColor()), GuiUtils.randomColor(), 10))),
                GuiUtils.snapBottom(GuiUtils.snapLeft(new ImageFrame(0, 0, GuiUtils.createImage(64, 64, GuiUtils.randomColor()), GuiUtils.randomColor(), 15))),
                GuiUtils.snapBottom(GuiUtils.snapRight(new ImageFrame(0, 0, GuiUtils.createImage(64, 64, GuiUtils.randomColor()), GuiUtils.randomColor(), 20))),
                GuiUtils.centerHorizontally(GuiUtils.centerVertically(new ImageFrame(0, 0, GuiUtils.createImage(64, 64, GuiUtils.randomColor()), GuiUtils.randomColor(), 5)))
        );
        //wp.get(5, Notification.class).show(LoremIpsum.getInstance().getWords(5), 2000);
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