package simple.engine.engine.modules;

import simple.engine.engine.Engine;
import simple.engine.engine.GameConfig;
import org.javatuples.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.LinkedList;

public class GraphicModule extends Module {

    @SuppressWarnings("unused")
    public static final int FIRST_LAYER = Integer.MIN_VALUE;
    @SuppressWarnings("unused")
    public static final int LAST_LAYER = Integer.MAX_VALUE;
    private final LinkedList<Pair<FrameListener, Integer>> frameListeners = new LinkedList<>();
    private final BufferedImage frameBuffer;

    public GraphicModule(GameConfig config) {
        super(config);
        JFrame frame = new JFrame() {
            @Override
            public void paint(Graphics g) {
                g.drawImage(frameBuffer, 0, 0, null);
            }
        };
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
            frameBuffer.createGraphics().clearRect(0, 0, config.getWidth(), config.getHeight());
            frameListeners.forEach(frameListener -> frameListener.getValue0().onNextFrame(frameBuffer.createGraphics()));
            Engine.guiModule.paint(frameBuffer.createGraphics());
            frame.repaint();
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
