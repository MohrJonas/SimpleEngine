package simple.engine.engine.modules;

import simple.engine.engine.GameConfig;

public class TimingModule extends Module {

    public TimingModule(GameConfig config) {
        super(config);
    }

    public void schedule(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runnable.run();
        }).start();
    }

    public void scheduleRepeatedly(Runnable runnable, float initialDelay, float interval) {
        new Thread(() -> {
            try {
                Thread.sleep((long) initialDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true) {
                runnable.run();
                try {
                    Thread.sleep((long) interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void scheduleMultiple(Runnable runnable, int initialDelay, int interval, int count) {
        new Thread(() -> {
            try {
                Thread.sleep(initialDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < count; i++) {
                runnable.run();
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
