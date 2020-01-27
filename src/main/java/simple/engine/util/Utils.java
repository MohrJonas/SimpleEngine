package simple.engine.util;

import org.javatuples.Tuple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Utils {

    public static <T extends List<? extends Tuple>> T sortTuple(T t, int position) {
        t.sort(Comparator.comparing(o -> ((Integer) o.getValue(position))));
        return t;
    }

    public static <T> void list(T[][] ts) {
        Arrays.stream(ts).forEach(ts1 -> System.out.println(Arrays.toString(ts1)));
    }

}
