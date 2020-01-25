package simple.engine.engine.util;

import org.javatuples.Tuple;

import java.util.Comparator;
import java.util.List;

public class Utils {

    public static <T extends List<? extends Tuple>> T sortTuple(T t, int position) {
        t.sort(Comparator.comparing(o -> ((Integer) o.getValue(position))));
        return t;
    }

}
