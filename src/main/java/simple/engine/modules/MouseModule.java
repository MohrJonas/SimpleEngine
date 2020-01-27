package simple.engine.modules;

import org.javatuples.Pair;
import simple.engine.Engine;
import simple.engine.util.GameConfig;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

public class MouseModule extends Module implements MouseListener, MouseMotionListener {

    private final LinkedList<Pair<Integer, Runnable>> mouseClicks = new LinkedList<>();
    private final LinkedList<Runnable> mouseMoves = new LinkedList<>();

    public MouseModule(GameConfig config) {
        super(config);
    }

    public void addMouseClickListener(int keycode, Runnable runnable) {
        mouseClicks.add(new Pair<>(keycode, runnable));
    }

    public void addMouseMoveListener(Runnable runnable) {
        mouseMoves.add(runnable);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) Engine.guiModule.onClick(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseClicks.stream().filter(p -> p.getValue0() == e.getButton()).findFirst().ifPresent(p -> p.getValue1().run());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseMoves.forEach(Runnable::run);
    }
}
