package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddOxygenAction;
import tm.action.IncomeDeltaAction;
import tm.action.PlaceTileAction;

public class ProtectedValley extends Card {

    public ProtectedValley() {
        super("Protected Valley", 23, Tags.BUILDING.combine(Tags.PLANT));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new PlaceTileAction(Tile.Type.MANGROVE),
            new AddOxygenAction(),
            new IncomeDeltaAction(new Resources(2))
        );

    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("2 money income", "Place greenery on ocean space", "(1 oxygen)");
    }
}
