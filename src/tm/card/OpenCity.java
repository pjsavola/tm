package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.PlaceTileAction;
import tm.requirement.OxygenRequirement;

public class OpenCity extends Card {

    public OpenCity() {
        super("Open City", 23, Tags.BUILDING_CITY, new OxygenRequirement(12, true));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Resources getResourceDelta() {
        return Resources.PLANT_2;
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(4, 0, 0, 0, -1, 0);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.CITY);
    }
}
