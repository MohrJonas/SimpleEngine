import com.thedeanda.lorem.LoremIpsum;
import simple.engine.engine.Engine;
import simple.engine.engine.GameConfig;
import simple.engine.engine.gui.WidgetPack;
import simple.engine.engine.gui.components.ImageFrame;
import simple.engine.engine.gui.components.Label;
import simple.engine.engine.gui.components.Rectangle;
import simple.engine.engine.gui.components.Widget;
import simple.engine.engine.modules.FrameListener;
import simple.engine.engine.modules.GraphicModule;
import simple.engine.engine.modules.Module;
import simple.engine.engine.graphics.ImageComposition;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args) {
        Engine.initialize(new GameConfig(), new ExitModule());
        WidgetPack wp = new WidgetPack(new Rectangle(0, 0, 200, 200, Color.CYAN), new Label(0, 300, "Hello World"), new ImageFrame(0, 0, Engine.storageModule.getImage("sample.png"), Color.CYAN, 5));
        ImageComposition c = new ImageComposition(new BufferedImage[]{
                Engine.storageModule.getImage("sample.png"),
                Engine.storageModule.getImage("sample.png"),
                Engine.storageModule.getImage("sample.png")
        }, new Point[]{
                new Point(50, 50),
                new Point(75, 30),
                new Point(100, 50)
        }, new Integer[]{
                0,
                1,
                2
        });
        Engine.storageModule.getAnimation("sampleGif.gif").setRepeating(true);
        Engine.graphicModule.addFrameListener(new FrameListener() {
            @Override
            public void onNextFrame(Graphics2D g) {
                g.setColor(Color.GREEN);
                g.fillRect(0, 0, 100, 100);
                g.drawImage(Engine.storageModule.getAnimation("sampleGif.gif").next(), 0, 200, null);
                c.paint(g);
                c.translate(1, 1);
            }
        }, GraphicModule.LAST_LAYER);
        Engine.guiModule.setWidgetPack(wp);
        Engine.guiModule.setActive(true);
        wp.get(0).addAnimation(Widget.MOVEMENT.VERTICAL, 400, 20);
        wp.get(1).addAnimation(Widget.MOVEMENT.HORIZONTAL, 400, 5);
        wp.get(1, Label.class).update("Hello World!");
        Engine.timingModule.scheduleRepeatedly(() -> ((Label) wp.get(1)).update(LoremIpsum.getInstance().getWords(1, 10)), 0, 1000);
        Engine.keyModule.addKeyListener(KeyEvent.VK_ESCAPE, () -> ((ExitModule) Engine.get(ExitModule.class)).exit());
        Engine.mouseModule.addMouseMoveListener(() -> System.out.print("."));
    }

    private static class ExitModule extends Module {

        public ExitModule() {
            super(Engine.getConfig());
        }

        public void exit() {
            System.exit(0);
        }
    }
}