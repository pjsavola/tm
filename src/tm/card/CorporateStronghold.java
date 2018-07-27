
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
import tm.action.IncomeDeltaAction;
import tm.action.PlaceTileAction;

public class CorporateStronghold extends Card {

    public CorporateStronghold() {
        super("Corporate Stronghold", 11, Tags.BUILDING.combine(Tags.CITY));
    }

    @Override
    public int getVPs() {
        return -2;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new PlaceTileAction(Tile.Type.CITY),
            new IncomeDeltaAction(new Resources(3, 0, 0, 0, -1, 0))
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("3 money income", "-1 energy income");
    }
}
