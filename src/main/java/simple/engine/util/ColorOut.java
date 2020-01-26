package simple.engine.util;

import java.io.PrintStream;

@SuppressWarnings("unused")
public class ColorOut {

    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    private static final String RESET = "\u001B[0m";

    public static void print(PrintStream stream, String text, String color) {
        stream.println(String.format("%s%s%s", color, text, RESET));
    }

    public static String asString(String text, String color) {
        return String.format("%s%s%s", color, text, RESET);
    }

}
