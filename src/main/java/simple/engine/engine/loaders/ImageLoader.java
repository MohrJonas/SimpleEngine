package simple.engine.engine.loaders;

import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.stream.Stream;

public class ImageLoader extends Loader<BufferedImage> {

    private final HashMap<String, BufferedImage> images = new HashMap<>();

    @Override
    public void preload(Stream<Path> path) {
        path.filter(p -> !FilenameUtils.getExtension(p.getFileName().toString()).isEmpty()).forEach(p -> {
            try {
                images.put(p.toString(), ImageIO.read(new File(String.valueOf(p))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println(images);
    }

    @Override
    public BufferedImage get(String name) {
        if (images.containsKey(name)) return images.get(name);
        throw new NullPointerException(name.concat(" isn't an image"));
    }
}
