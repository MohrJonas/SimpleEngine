package simple.engine.util;

public class OverflowingInteger {

    private int top;
    private int bottom;
    private int i = 0;

    public OverflowingInteger(int i, int top, int bottom) {
        this.i = i;
        this.top = top;
        this.bottom = bottom;
    }

    public int increment() {
        i++;
        fix();
        return i;
    }

    public int get() {
        return i;
    }

    public int decrement() {
        i--;
        fix();
        return i;
    }

    private void fix() {
        if (i > top) i = bottom;
        else if (i < bottom) i = top;
    }
}
