package simple.engine.engine.loaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public abstract class Loader<T> {

    public abstract void preload(String folder);

    public abstract T get(String name);

    protected List<String> getResourceFiles(String path) {
        List<String> filenames = new ArrayList<>();
        try {
            try (
                    InputStream in = getResourceAsStream(path);
                    BufferedReader br = new BufferedReader(new InputStreamReader(in))
            ) {
                String resource;
                while ((resource = br.readLine()) != null) {
                    filenames.add(resource);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filenames;
    }

    protected InputStream getResourceAsStream(String resource) {
        final InputStream in = getContextClassLoader().getResourceAsStream(resource);
        return in == null ? getClass().getResourceAsStream(resource) : in;
    }

    protected ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

}
