package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class GeothermalPower extends Card {

    public GeothermalPower() {
        super("Geothermal Power", 11, Tags.BUILDING_POWER);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(new Resources(0, 0, 0, 0, 2, 0));
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("2 energy income");
    }
}
