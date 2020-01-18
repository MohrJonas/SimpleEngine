package simple.engine.engine.modules;

import com.google.common.annotations.Beta;
import com.google.common.io.Resources;
import simple.engine.engine.GameConfig;
import simple.engine.engine.ImageOptimizer;
import simple.engine.engine.loaders.ImageLoader;
import simple.engine.engine.loaders.SoundLoader;

import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Beta
public class StorageModule extends Module {

    private final String imagePrefix = "images";
    private final String soundPrefix = "sounds";
    private final ImageLoader imageLoader = new ImageLoader();
    private final SoundLoader soundLoader = new SoundLoader();

    public StorageModule(GameConfig config) {
        super(config);
        try {
            String imagePath = Resources.getResource(imagePrefix).getPath();
            imageLoader.preload(Files.walk(Paths.get(imagePath.substring(1))));
            String soundPath = Resources.getResource(soundPrefix).getPath();
            soundLoader.preload(Files.walk(Paths.get(soundPath.substring(1))));
        } catch (NullPointerException | IOException | IllegalArgumentException e) {
            System.err.println("An error occurred during loading");
            e.printStackTrace();
        }
    }

    public BufferedImage getImage(String name) {
        return ImageOptimizer.optimize(imageLoader.get(name));
    }

    public Clip getClip(String name) {
        return soundLoader.get(name);
    }
}
