package simple.engine.util;

public class Counter {

    private final int start;
    private final int end;
    private final int stepSize;
    private final boolean reversed;
    private int current;
    private boolean finished;

    public Counter(int start, int end, int stepSize) {
        this.start = start;
        this.end = end;
        reversed = start > end;
        this.stepSize = reversed ? -stepSize : stepSize;
        current = start;
        if (Math.abs(start - end) % stepSize != 0) System.err.println("The stepSize doesn't exactly fit the distance");
    }

    public boolean hasFinished() {
        return finished;
    }

    public synchronized int next() {
        if (finished) return current;
        current += stepSize;
        if (reversed) {
            if (current + stepSize < end) finished = true;
            return get();
        }
        if (current + stepSize > end) finished = true;
        return get();
    }

    public int get() {
        return current;
    }

    @Override
    public String toString() {
        return String.format("%d====%d====%d", start, current, end);
    }
}
