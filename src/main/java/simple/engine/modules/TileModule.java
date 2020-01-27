package simple.engine.modules;

import simple.engine.util.GameConfig;
import simple.engine.tiles.TileMap;

public class TileModule extends Module {

    private TileMap map;

    public TileModule(GameConfig config) {
        super(config);
    }

    public void setMap(TileMap map) {
        this.map = map;
    }
}
