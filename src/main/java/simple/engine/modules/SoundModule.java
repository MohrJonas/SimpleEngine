package simple.engine.modules;

import simple.engine.util.GameConfig;

import javax.sound.sampled.Clip;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;

public final class SoundModule extends Module {

    private final GameConfig config;

    public SoundModule(GameConfig config) {
        super(config);
        this.config = config;
    }

    public synchronized void play(Clip clip) {
        ((FloatControl) clip.getControl(FloatControl.Type.VOLUME)).setValue(config.getDefaultVolume());
        clip.start();
    }

}
