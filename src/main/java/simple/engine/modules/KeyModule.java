package simple.engine.modules;

import org.javatuples.Pair;
import simple.engine.GameConfig;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class KeyModule extends Module implements KeyListener {

    private final LinkedList<Pair<Integer, Runnable>> keys = new LinkedList<>();

    public KeyModule(GameConfig config) {
        super(config);
    }


    public void addKeyListener(int keycode, Runnable runnable) {
        keys.add(new Pair<>(keycode, runnable));
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys.stream().filter(p -> p.getValue0() == e.getKeyCode()).findFirst().ifPresent(p -> p.getValue1().run());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
