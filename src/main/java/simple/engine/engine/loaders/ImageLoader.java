package simple.engine.engine.loaders;

import simple.engine.engine.graphics.ImageOptimizer;
import simple.engine.engine.util.ColorOut;
import simple.engine.engine.util.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ImageLoader extends Loader<BufferedImage> {

    @Override
    public void preload(Stream<Path> path) {
        path.forEach(p -> {
            try {
                content.put(p.getFileName().toString(), ImageOptimizer.optimize(ImageIO.read(p.toFile())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Logger.log(ColorOut.asString(String.format("Loaded %d image(s) from files.", content.values().size()), ColorOut.GREEN), "resources");
    }

    @Override
    public BufferedImage get(String name) {
        if (content.containsKey(name)) return content.get(name);
        if (content.containsKey(name.replace(".", ""))) return content.get(name.replace(".", ""));
        throw new NullPointerException(name.concat(" isn't an image"));
    }
}
