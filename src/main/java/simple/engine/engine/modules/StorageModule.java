package simple.engine.engine.modules;

import simple.engine.engine.GameConfig;
import simple.engine.engine.loaders.ImageLoader;
import simple.engine.engine.loaders.SoundLoader;

import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;

public class StorageModule extends Module {

    private final String imagePrefix = "/images/";
    private final String soundPrefix = "/sounds/";
    private final ImageLoader imageLoader = new ImageLoader();
    private final SoundLoader soundLoader = new SoundLoader();

    public StorageModule(GameConfig config) {
        super(config);
        //imageLoader.preload(imagePrefix);
        //soundLoader.preload(soundPrefix);
    }

    public BufferedImage loadImage(String file) {
        return imageLoader.get(imagePrefix.concat(file));
    }

    public Clip loadSound(String file) {
        return soundLoader.get(soundPrefix.concat(file));
    }
}
