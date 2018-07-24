package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.PlaceTileAction;

// Remove 2 plants is done from dummy player
public class MiningArea extends Card {

    public MiningArea() {
        super("Mining Area", 4, Tags.BUILDING);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.MINING_AREA);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Place tile on steel or titanium", "1 income of that type", "Must be placed next to your tile");
    }
}
