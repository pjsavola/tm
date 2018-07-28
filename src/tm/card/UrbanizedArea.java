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
import tm.action.PlaceTileAction;

public class UrbanizedArea extends Card {

    public UrbanizedArea() {
        super("Urbanized Area", 10, Tags.BUILDING_CITY);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(2, 0, 0, 0, -1, 0);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.URBANIZED_AREA);
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
