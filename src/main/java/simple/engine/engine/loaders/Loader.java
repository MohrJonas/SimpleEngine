package simple.engine.engine.loaders;

import java.nio.file.Path;
import java.util.stream.Stream;

public abstract class Loader<T> {

    public abstract void preload(Stream<Path> path);

    public abstract T get(String name);
}
