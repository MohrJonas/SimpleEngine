package simple.engine.modules;

import simple.engine.GameConfig;
import simple.engine.TileMap;

public class TileModule extends Module {

    private TileMap map;

    public TileModule(GameConfig config) {
        super(config);
    }

    public void setMap(TileMap map) {
        this.map = map;
    }
}
