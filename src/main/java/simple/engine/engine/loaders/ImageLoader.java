package simple.engine.engine.loaders;

import simple.engine.engine.ImageOptimizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.stream.Stream;

public class ImageLoader extends Loader<BufferedImage> {

    private final HashMap<String, BufferedImage> images = new HashMap<>();

    @Override
    public void preload(Stream<Path> path) {
        path.forEach(p -> {
            try {
                images.put(p.getFileName().toString(), ImageOptimizer.optimize(ImageIO.read(p.toFile())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println(images);
    }

    @Override
    public BufferedImage get(String name) {
        if (images.containsKey(name)) return images.get(name);
        if (images.containsKey(name.replace(".", ""))) return images.get(name.replace(".", ""));
        throw new NullPointerException(name.concat(" isn't an image"));
    }
}
