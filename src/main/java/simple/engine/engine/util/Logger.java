package simple.engine.engine.util;

import java.util.HashMap;

public class Logger {

    private static final HashMap<String, Boolean> levels = new HashMap<>();

    public static void addLevel(String level, boolean enabled) {
        Logger.levels.put(level, enabled);
    }

    public static void enableLevel(String level) {
        if (levels.containsKey(level)) levels.put(level, true);
    }

    public static void disableLevel(String level) {
        if (levels.containsKey(level)) levels.put(level, false);
    }

    public static void log(String s, String level) {
        if (level.contains(level) && levels.get(level)) System.out.println(s);
    }

    public static HashMap<String, Boolean> getLevels() {
        return levels;
    }
}
