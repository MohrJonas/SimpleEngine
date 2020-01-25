package simple.engine.engine.modules;

import com.google.common.annotations.Beta;
import com.madgag.gif.fmsware.GifDecoder;
import org.apache.commons.io.FilenameUtils;
import simple.engine.engine.GameConfig;
import simple.engine.engine.loaders.GifLoader;
import simple.engine.engine.loaders.ImageLoader;
import simple.engine.engine.loaders.SoundLoader;
import simple.engine.engine.util.ArrayUtils;
import simple.engine.engine.util.Gif;

import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Beta
public class StorageModule extends Module {

    private final String imagePrefix = "images";
    private final String soundPrefix = "sounds";
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

    public Gif getGif(String name) {
        return gifLoader.get(name);
    }

    public BufferedImage getImage(String name) {
        return imageLoader.get(name);
    }

    public Clip getClip(String name) {
        return soundLoader.get(name);
    }
}
