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

public class NaturalPreserve extends Card {

    public NaturalPreserve() {
        super("Natural Preserve", 9, Tags.SCIENCE.combine(Tags.BUILDING), new OxygenRequirement(4, false));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new PlaceTileAction(Tile.Type.NATURAL_PRESERVE, true),
            new IncomeDeltaAction(Resources.MONEY)
        );
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Oxygen must be 4% or less");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 money income", "Place tile next to no other tile");
    }
}
