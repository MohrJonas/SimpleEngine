package simple.engine.modules;

import simple.engine.GameConfig;

import javax.sound.sampled.Clip;

public final class SoundModule extends Module {

    private final GameConfig config;

    public SoundModule(GameConfig config) {
        super(config);
        this.config = config;
    }

    public synchronized void play(Clip clip) {
        clip.start();
    }

}
