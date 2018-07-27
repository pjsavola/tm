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
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.action.PlaceTileAction;
import tm.requirement.OxygenRequirement;

public class CupolaCity extends Card {

    public CupolaCity() {
        super("Cupola City", 16, Tags.BUILDING.combine(Tags.CITY), new OxygenRequirement(9, false));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new PlaceTileAction(Tile.Type.CITY),
            new IncomeDeltaAction(new Resources(3, 0, 0, 0, -1, 0))
        );
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Oxygen must be 9% or less");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("3 money income", "-1 energy income");
    }
}
