package simple.engine.util;

import java.util.Arrays;

public class ArrayUtils {

    public static <T> boolean contains(T t, T[] ts) {
        return Arrays.asList(ts).contains(t);
    }

}
