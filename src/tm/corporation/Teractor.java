package tm.corporation;

import java.util.Arrays;
import java.util.List;

import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ResourceDeltaAction;

public class Teractor extends Corporation {

    public Teractor() {
        super("Teractor", Tags.EARTH);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ResourceDeltaAction(new Resources(60));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("60 money", "Cards with earth tag cost 3 less");
    }
}
