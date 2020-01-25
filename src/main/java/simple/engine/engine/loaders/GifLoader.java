package simple.engine.engine.loaders;

import com.madgag.gif.fmsware.GifDecoder;
import simple.engine.engine.util.ColorOut;
import simple.engine.engine.util.Gif;

import java.nio.file.Path;
import java.util.stream.Stream;

public class GifLoader extends Loader<Gif> {


    @Override
    public void preload(Stream<Path> path) {
        path.forEach(p -> {
            content.put(p.getFileName().toString(), new Gif(new GifDecoder() {{
                read(p.toString());
            }}));
        });
        ColorOut.print(System.out, String.format("Loaded %d gif(s) from files.", content.values().size()), ColorOut.GREEN);
    }

    @Override
    public Gif get(String name) {
        if (content.containsKey(name)) return content.get(name);
        if (content.containsKey(name.replace(".", ""))) return content.get(name.replace(".", ""));
        throw new NullPointerException(name.concat(" isn't a gif"));
    }
}
