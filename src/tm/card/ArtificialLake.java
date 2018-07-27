package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.AddWaterAction;
import tm.requirement.TemperatureRequirement;

public class ArtificialLake extends Card {

    public ArtificialLake() {
        super("Artificial Lake", 15, Tags.BUILDING, new TemperatureRequirement(-6, true));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new AddWaterAction(Tile.Type.ARTIFICIAL_LAKE);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires -6C or warmer");
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 ocean to non-water tile");
    }
}
