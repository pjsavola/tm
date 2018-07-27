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

public class UndergroundCity extends Card {

    public UndergroundCity() {
        super("Underground City", 18, Tags.BUILDING_CITY);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new PlaceTileAction(Tile.Type.CITY),
            new IncomeDeltaAction(new Resources(0, 2, 0, 0, -2, 0))
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("2 steel income", "-2 energy income");
    }
}
