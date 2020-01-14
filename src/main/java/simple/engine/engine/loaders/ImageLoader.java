package simple.engine.engine.loaders;

import simple.engine.engine.ImageOptimizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Objects;

public class ImageLoader extends Loader<BufferedImage> {

    private final HashMap<String, BufferedImage> images = new HashMap<>();

    @Override
    public void preload(String folder) {
        System.out.println(this.getClass().getClassLoader().getResource("images/Wallpaper.png"));
        System.out.println(this.getClass().getClassLoader().getResource(folder));
        try {
            Files.walk(Paths.get(Objects.requireNonNull(this.getClass().getClassLoader().getResource(folder)).toURI())).filter(Files::isRegularFile).forEach(path -> {
                System.out.println(path.toAbsolutePath());
                try {
                    System.out.println(path.toString());
                    images.put(path.toFile().getName(), ImageOptimizer.optimize(ImageIO.read(path.toFile())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println(images);
    }

    @Override
    public BufferedImage get(String name) {
        if(images.containsKey(name)) return images.get(name);
        //if(name.contains(".") && images.containsKey(name.split(".")[0])) return images.get(name.split(".")[0]);
        throw new NullPointerException(name.concat(" isn't a file"));
    }
}
