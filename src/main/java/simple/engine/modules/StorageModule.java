package simple.engine.modules;

import org.apache.commons.io.FilenameUtils;
import simple.engine.GameConfig;
import simple.engine.graphics.Animation;
import simple.engine.loaders.GifLoader;
import simple.engine.loaders.ImageLoader;
import simple.engine.loaders.SoundLoader;
import simple.engine.util.ArrayUtils;

import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class StorageModule extends Module {

    private final ImageLoader imageLoader = new ImageLoader();
    private final SoundLoader soundLoader = new SoundLoader();
    private final GifLoader gifLoader = new GifLoader();

    public StorageModule(GameConfig config, Class<?> clazz) {
        super(config);
        try {
            String[] imageExtensions = new String[]{"png", "jpg", "jpeg", "bmp"};
            String[] soundExtensions = new String[]{"wav"};
            String[] gifExtensions = new String[]{"gif"};
            imageLoader.preload(Files.walk(new File(clazz.getProtectionDomain().getCodeSource().getLocation().getPath()).toPath()).filter(path ->
                    ArrayUtils.contains(FilenameUtils.getExtension(path.getFileName().toString()), imageExtensions)));
            soundLoader.preload(Files.walk(new File(clazz.getProtectionDomain().getCodeSource().getLocation().getPath()).toPath()).filter(path ->
                    ArrayUtils.contains(FilenameUtils.getExtension(path.getFileName().toString()), soundExtensions)));
            gifLoader.preload(Files.walk(new File(clazz.getProtectionDomain().getCodeSource().getLocation().getPath()).toPath()).filter(path ->
                    ArrayUtils.contains(FilenameUtils.getExtension(path.getFileName().toString()), gifExtensions)));
        } catch (IOException e) {
            System.err.println(e.getCause().getMessage());
        }
    }

    public Animation getAnimation(String name) {
        return gifLoader.get(name);
    }

    public BufferedImage getImage(String name) {
        return imageLoader.get(name);
    }

    public Clip getClip(String name) {
        return soundLoader.get(name);
    }
}
