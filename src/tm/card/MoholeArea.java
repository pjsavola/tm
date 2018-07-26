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

public class MoholeArea extends Card {

    public MoholeArea() {
        super("Mohole Area", 20, Tags.BUILDING);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new PlaceTileAction(Tile.Type.MOHOLE_AREA),
            new IncomeDeltaAction(new Resources(0, 0, 0, 0, 0, 4))
        );

    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Place tile on ocean space", "4 heat income");
    }
}
