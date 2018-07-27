package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class PowerPlant extends Card {

    public PowerPlant() {
        super("Power Plant", 4, Tags.BUILDING_POWER);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(Resources.ENERGY);
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 energy income");
    }
}
