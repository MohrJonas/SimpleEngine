package simple.engine.modules;

import simple.engine.util.GameConfig;

import java.util.concurrent.TimeUnit;

public class TimingModule extends Module {

    public TimingModule(GameConfig config) {
        super(config);
    }

    public void schedule(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runnable.run();
        }).start();
    }

    public void scheduleRepeatedly(Runnable runnable, int initialDelay, int interval) {
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(initialDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true) {
                runnable.run();
                try {
                    TimeUnit.MILLISECONDS.sleep(interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void scheduleMultiple(Runnable runnable, int initialDelay, int interval, int count) {
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(initialDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < count; i++) {
                runnable.run();
                try {
                    TimeUnit.MILLISECONDS.sleep(interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
