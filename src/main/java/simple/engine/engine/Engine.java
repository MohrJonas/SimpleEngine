package simple.engine.engine;

import simple.engine.engine.modules.*;
import simple.engine.engine.util.Logger;

import java.util.Arrays;
import java.util.HashMap;

public class Engine {

    public static StorageModule storageModule;
    public static GuiModule guiModule;
    public static GraphicModule graphicModule;
    public static SoundModule soundModule;
    public static TimingModule timingModule;
    public static KeyModule keyModule;
    public static MouseModule mouseModule;
    private static GameConfig config;
    private static boolean ready;
    private static final HashMap<Class<? extends Module>, Module> modules = new HashMap<>();

    @SuppressWarnings("deprecation")
    public static void initialize(GameConfig config, Module... additionalModules) {
        Engine.config = config;
        Logger.addLevels("fps");
        Logger.addLevels("resources");
        Logger.disableLevel("fps");
        timingModule = new TimingModule(config);
        storageModule = new StorageModule(config, sun.reflect.Reflection.getCallerClass(2));
        keyModule = new KeyModule(config);
        mouseModule = new MouseModule(config);
        graphicModule = new GraphicModule(config);
        guiModule = new GuiModule(config);
        soundModule = new SoundModule(config);
        Arrays.stream(additionalModules).forEach(module -> modules.put(module.getClass(), module));
        ready = true;
    }

    public static Module get(Class<? extends Module> c) {
        return modules.get(c);
    }

    public static GameConfig getConfig() {
        return config;
    }

    public static boolean isReady() {
        return ready;
    }

}
