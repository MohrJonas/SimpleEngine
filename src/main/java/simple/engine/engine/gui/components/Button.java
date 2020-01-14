package simple.engine.engine.gui.components;

public abstract class Button extends StaticWidget {

    private final Runnable runnable;

    protected Button(int x, int y, int width, int height, Runnable runnable) {
        super(x, y, width, height);
        this.runnable = runnable;
    }

    public boolean onClick(int x, int y) {
        if (rectangle.contains(x, y)) {
            runnable.run();
            return true;
        }
        return false;
    }
}
