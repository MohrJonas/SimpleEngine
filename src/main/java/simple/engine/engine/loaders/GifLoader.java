package simple.engine.engine.loaders;

import com.madgag.gif.fmsware.GifDecoder;
import simple.engine.engine.util.ColorOut;
import simple.engine.engine.graphics.Animation;
import simple.engine.engine.util.Logger;

import java.nio.file.Path;
import java.util.stream.Stream;

public class GifLoader extends Loader<Animation> {


    @Override
    public void preload(Stream<Path> path) {
        path.forEach(p -> content.put(p.getFileName().toString(), new Animation(new GifDecoder() {{
            read(p.toString());
        }})));
        Logger.log(ColorOut.asString(String.format("Loaded %d gif(s) from files.", content.values().size()), ColorOut.GREEN), "resources");
    }

    @Override
    public Animation get(String name) {
        if (content.containsKey(name)) return content.get(name);
        if (content.containsKey(name.replace(".", ""))) return content.get(name.replace(".", ""));
        throw new NullPointerException(name.concat(" isn't a gif"));
    }
}
