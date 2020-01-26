package simple.engine.modules;

import simple.engine.GameConfig;

public class Module {

    protected final GameConfig config;

    public Module(GameConfig config) {
        this.config = config;
    }

    public void dispose() {
    }

}
