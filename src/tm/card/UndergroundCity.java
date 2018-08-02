package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.PlaceTileAction;

public class UndergroundCity extends Card {

    public UndergroundCity() {
        super("Underground City", 18, Tags.BUILDING_CITY);
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(0, 2, 0, 0, -2, 0);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.CITY);
    }
}
