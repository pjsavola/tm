package tm.corporation;

import java.util.Arrays;
import java.util.List;

import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class Ecoline extends Corporation {

    public Ecoline() {
        super("Ecoline", Tags.PLANT);
    }

    @Override
    public Resources getResourceDelta(Game game) {
        return new Resources(36, 0, 0, 3, 0, 0);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(Resources.PLANT_2);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("2 plant income", "Greenery costs 7 plants");
    }
}
