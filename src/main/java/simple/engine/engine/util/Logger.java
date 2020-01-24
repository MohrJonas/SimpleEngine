package simple.engine.engine.util;

import java.util.Arrays;
import java.util.HashMap;

public class Logger {

    private static final HashMap<String, Boolean> levels = new HashMap<>();

    public static void addLevels(String... levels) {
        Arrays.stream(levels).forEach(s -> Logger.levels.put(s, true));
    }

    public static void enableLevel(String level) {
        if (levels.containsKey(level)) levels.put(level, true);
    }

    public static void disableLevel(String level) {
        if (levels.containsKey(level)) levels.put(level, false);
    }

    public static void log(String s, String level) {
        if (levels.get(level)) System.out.println(s);
    }
}
