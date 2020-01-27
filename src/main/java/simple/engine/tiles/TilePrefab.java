package simple.engine.tiles;

import java.io.Serializable;

public class TilePrefab implements Serializable {

    public final String name;
    public final String image;
    public final float breakLevel;
    public final float breakTime;

    public TilePrefab(String image, String name, float breakLevel, float breakTime) {
        this.name = name;
        this.image = image;
        this.breakLevel = breakLevel;
        this.breakTime = breakTime;
    }

    @Override
    public String toString() {
        return "TilePrefab{" + "name='" + name + '\'' + ", image='" + image + '\'' + ", breakLevel=" + breakLevel + ", breakTime=" + breakTime + "}";
    }
}
