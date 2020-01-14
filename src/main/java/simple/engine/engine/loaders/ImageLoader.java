package simple.engine.engine.loaders;

import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class ImageLoader extends Loader<BufferedImage> {

    private final HashMap<String, BufferedImage> images = new HashMap<>();

    @Override
    public void preload(String folder) {
        getResourceFiles(folder).stream().filter(s -> !FilenameUtils.getExtension(s).isEmpty()).forEach(s -> {
            try {
                images.put(s, ImageIO.read(ImageLoader.class.getResource(folder.concat(s))));
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
