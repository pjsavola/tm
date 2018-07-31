package tm.corporation;

import java.util.Collections;
import java.util.List;

import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class Ecoline extends Corporation {

    public Ecoline() {
        super("Ecoline", Tags.PLANT);
    }

    @Override
    public Resources getResourceDelta(Game game) {
        return new Resources(36, 0, 0, 3, 0, 0);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return Resources.PLANT_2;
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("Greenery costs 7 plants");
    }
}
