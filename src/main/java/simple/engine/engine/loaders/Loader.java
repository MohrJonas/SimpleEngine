package simple.engine.engine.loaders;

public abstract class Loader<T> {

    public abstract void preload(String folder);

    public abstract T get(String name);

}
