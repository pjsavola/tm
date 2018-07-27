package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

// Remove 2 heat income is done from dummy player
public class HeatTrappers extends Card {

    public HeatTrappers() {
        super("Heat Trappers", 6, Tags.BUILDING.combine(Tags.POWER));
    }

    @Override
    public int getVPs() {
        return -1;
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
