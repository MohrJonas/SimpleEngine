package simple.engine.modules;

import org.apache.commons.io.FilenameUtils;
import simple.engine.graphics.Animation;
import simple.engine.loaders.GifLoader;
import simple.engine.loaders.ImageLoader;
import simple.engine.loaders.SoundLoader;
import simple.engine.util.ArrayUtils;
import simple.engine.util.GameConfig;

import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

public class StorageModule extends Module {

    private final HashMap<Integer, Object> values = new HashMap<>();
    private final ImageLoader imageLoader = new ImageLoader();
    private final SoundLoader soundLoader = new SoundLoader();
    private final GifLoader gifLoader = new GifLoader();
    private final Class<?> clazz;

    public StorageModule(GameConfig config, Class<?> clazz) {
        super(config);
        this.clazz = clazz;
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

    public File getRelativeFile(String path) {
        return new File(clazz.getProtectionDomain().getCodeSource().getLocation().getPath().concat(path));
    }

    public void putValue(int id, Object o) {
        values.put(id, o);
    }

    public <T> T retrieveValue(int id, Class<T> c) {
        if (!values.containsKey(id))
            throw new IllegalArgumentException("Widget with ID ".concat(String.valueOf(id)).concat(" doesn't exist"));
        return c.cast(values.get(id));
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
