package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.PlaceTileAction;
import tm.requirement.OxygenRequirement;

public class CupolaCity extends Card {

    public CupolaCity() {
        super("Cupola City", 16, Tags.BUILDING_CITY, new OxygenRequirement(9, false));
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(3, 0, 0, 0, -1, 0);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.CITY);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Oxygen must be 9% or less");
    }
}
