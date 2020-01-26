package simple.engine.gui.components;

public abstract class DynamicWidget<T> extends Widget {

    public DynamicWidget(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public abstract void update(T t);

}
