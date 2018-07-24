package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.PlaceTileAction;

public class MiningRights extends Card {

    public MiningRights() {
        super("Mining Rights", 9, Tags.BUILDING);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.MINING_RIGHTS);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Place tile on steel or titanium", "1 income of that type");
    }
}
