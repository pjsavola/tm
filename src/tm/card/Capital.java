package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.PlaceTileAction;
import tm.requirement.OceanRequirement;

public class Capital extends Card {

    public Capital() {
        super("Capital", 26, Tags.BUILDING_CITY, new OceanRequirement(4, true));
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(5, 0, 0, 0, -2, 0);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.CAPITAL);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 4 ocean tiles");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("5 money income", "-2 energy income");
    }
}
