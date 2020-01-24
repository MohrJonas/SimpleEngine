import com.google.common.io.Resources;
import com.thedeanda.lorem.LoremIpsum;
import org.apache.commons.io.FilenameUtils;
import simple.engine.engine.Engine;
import simple.engine.engine.GameConfig;
import simple.engine.engine.gui.WidgetPack;
import simple.engine.engine.gui.components.ImageFrame;
import simple.engine.engine.gui.components.Label;
import simple.engine.engine.gui.components.Rectangle;
import simple.engine.engine.gui.components.Widget;
import simple.engine.engine.modules.FrameListener;
import simple.engine.engine.modules.GraphicModule;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        Engine.initialize(new GameConfig() {{
            setFps(60);
            setHeight(600);
            setWidth(600);
            setDefaultVolume(1);
            setFullscreen(true);
        }});
        WidgetPack wp = new WidgetPack(new Rectangle(0, 0, 200, 200, Color.CYAN), new Label(0, 300, "Hello World"), new ImageFrame(0, 0, ImageIO.read(Resources.getResource("sample.png")), Color.CYAN, 5));
        Engine.graphicModule.addFrameListener(new FrameListener() {
            @Override
            public void onNextFrame(Graphics2D g) {
                g.setColor(Color.GREEN);
                g.fillRect(0, 0, 100, 100);
            }
        }, GraphicModule.LAST_LAYER);
        Engine.guiModule.setWidgetPack(wp);
        Engine.guiModule.setActive(true);
        wp.get(0).addAnimation(Widget.MOVEMENT.VERTICAL, 400, 20);
        wp.get(1).addAnimation(Widget.MOVEMENT.HORIZONTAL, 400, 5);
        Engine.timingModule.scheduleRepeatedly(() -> ((Label) wp.get(1)).update(LoremIpsum.getInstance().getWords(1, 10)), 0, 1000);
    }
}