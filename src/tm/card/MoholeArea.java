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

public class MoholeArea extends Card {

    public MoholeArea() {
        super("Mohole Area", 20, Tags.BUILDING);
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(0, 0, 0, 0, 0, 4);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.MOHOLE_AREA);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Place tile on ocean");
    }
}
