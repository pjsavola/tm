package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.action.PlaceTileAction;

public class UrbanizedArea extends Card {

    public UrbanizedArea() {
        super("Urbanized Area", 10, Tags.BUILDING.combine(Tags.CITY));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new PlaceTileAction(Tile.Type.URBANIZED_AREA),
            new IncomeDeltaAction(new Resources(2, 0, 0, 0, -1, 0))
        );
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Place next to at least 2 city tiles");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("2 money income", "-1 energy income");
    }
}
