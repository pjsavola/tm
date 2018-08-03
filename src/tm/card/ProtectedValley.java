package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddOxygenAction;
import tm.action.PlaceTileAction;

public class ProtectedValley extends Card {

    public ProtectedValley() {
        super("Protected Valley", 23, Tags.BUILDING.combine(Tags.PLANT));
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(2);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(new PlaceTileAction(Tile.Type.MANGROVE), new AddOxygenAction());

    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Place greenery on ocean");
    }
}
