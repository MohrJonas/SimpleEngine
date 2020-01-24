package simple.engine.engine.modules;

import com.google.common.base.Stopwatch;
import org.javatuples.Pair;
import simple.engine.engine.Engine;
import simple.engine.engine.GameConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class GraphicModule extends Module {

    public static final int FIRST_LAYER = Integer.MIN_VALUE;
    public static final int LAST_LAYER = Integer.MAX_VALUE;
    private final Stopwatch stopwatch = Stopwatch.createUnstarted();
    private final LinkedList<Pair<FrameListener, Integer>> frameListeners = new LinkedList<>();
    private final BufferedImage frameBuffer;

    public GraphicModule(GameConfig config) {
        super(config);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        if (config.isFullscreen()) {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frameBuffer = new BufferedImage((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight(), BufferedImage.TYPE_INT_ARGB);
        } else {
            frame.setSize(config.getWidth(), config.getHeight());
            frameBuffer = new BufferedImage(config.getWidth(), config.getHeight(), BufferedImage.TYPE_INT_ARGB);
        }
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Engine.timingModule.scheduleRepeatedly(() -> {
            stopwatch.start();
            frameBuffer.createGraphics().clearRect(0, 0, frameBuffer.getWidth(), frame.getHeight());
            frameListeners.forEach(frameListener -> frameListener.getValue0().onNextFrame(frameBuffer.createGraphics()));
            Engine.guiModule.paint(frameBuffer.createGraphics());
            frame.getGraphics().drawImage(frameBuffer, 0, 0, null);
            System.out.println(1000 / stopwatch.elapsed(TimeUnit.MILLISECONDS) + " fps");
            stopwatch.reset();
        }, 0, 17);
        frame.addKeyListener(Engine.keyModule);
        frame.addMouseListener(Engine.mouseModule);
        frame.addMouseMotionListener(Engine.mouseModule);
    }

    public void addFrameListener(FrameListener listener, int layer) {
        if (frameListeners.stream().anyMatch(objects -> objects.getValue1() == layer))
            System.err.println("Warning: A layer with that ID has already been registered (ID: ".concat(layer == Integer.MIN_VALUE ? "FIRST_LAYER" : (layer == Integer.MAX_VALUE ? "LAST_LAYER" : String.valueOf(layer))).concat(")"));
        frameListeners.add(new Pair<>(listener, layer));
        frameListeners.sort(Comparator.comparing(Pair::getValue1));
    }
}
