package simple.engine.modules;

import com.google.common.base.Stopwatch;
import org.javatuples.Pair;
import simple.engine.Engine;
import simple.engine.util.GameConfig;
import simple.engine.util.Logger;
import simple.engine.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class GraphicModule extends Module {

    @SuppressWarnings("unused")
    public static final int FIRST_LAYER = Integer.MIN_VALUE;
    @SuppressWarnings("unused")
    public static final int LAST_LAYER = Integer.MAX_VALUE;
    private final Stopwatch stopwatch = Stopwatch.createUnstarted();
    private final LinkedList<Pair<FrameListener, Integer>> frameListeners = new LinkedList<>();
    private BufferedImage frameBuffer;
    private JFrame frame;

    public void setSize(int width, int height) {
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frameBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    public GraphicModule(GameConfig config) {
        super(config);
        frame = new JFrame();
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
        frame.addKeyListener(Engine.keyModule);
        frame.addMouseListener(Engine.mouseModule);
        frame.addMouseMotionListener(Engine.mouseModule);
        frame.addMouseWheelListener(Engine.mouseModule);
        frame.setVisible(true);
        Engine.timingModule.scheduleRepeatedly(() -> {
            stopwatch.start();
            frameBuffer.createGraphics().clearRect(0, 0, frameBuffer.getWidth(), frame.getHeight());
            frameListeners.forEach(frameListener -> {
                Graphics2D g = frameBuffer.createGraphics();
                AffineTransform transform = g.getTransform();
                frameListener.getValue0().onNextFrame(g);
                g.setTransform(transform);
            });
            Engine.guiModule.paint(frameBuffer.createGraphics());
            frame.getGraphics().drawImage(frameBuffer, 0, 0, null);
            if (stopwatch.elapsed(TimeUnit.MILLISECONDS) != 0)
                Logger.log(1000 / stopwatch.elapsed(TimeUnit.MILLISECONDS) + " fps", "fps");
            stopwatch.reset();
        }, 0, 1000 / config.getFps());
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        if (frame != null)
            this.frame = frame;
    }

    public void addFrameListener(FrameListener listener, int layer) {
        if (frameListeners.stream().anyMatch(objects -> objects.getValue1() == layer))
            System.err.println("Warning: A layer with that ID has already been registered (ID: ".concat(layer == Integer.MIN_VALUE ? "FIRST_LAYER" : (layer == Integer.MAX_VALUE ? "LAST_LAYER" : String.valueOf(layer))).concat(")"));
        frameListeners.add(new Pair<>(listener, layer));
        Utils.sortTuple(frameListeners, 1);
    }
}
