
package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.PlaceTileAction;

public class CorporateStronghold extends Card {

    public CorporateStronghold() {
        super("Corporate Stronghold", 11, Tags.BUILDING_CITY);
    }

    @Override
    public int getVPs() {
        return -2;
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(3, 0, 0, 0, -1, 0);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.CITY);
    }
}
