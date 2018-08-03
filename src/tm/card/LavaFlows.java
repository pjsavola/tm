package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddTemperatureAction;
import tm.action.PlaceTileAction;

public class LavaFlows extends Card {

    public LavaFlows() {
        super("Lava Flows", 18, Tags.EVENT);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new PlaceTileAction(Tile.Type.LAVA_FLOWS),
            new AddTemperatureAction(),
            new AddTemperatureAction()
        );
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Place tile on volcano");
    }
}
