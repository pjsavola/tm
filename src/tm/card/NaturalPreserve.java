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
import tm.requirement.OxygenRequirement;

public class NaturalPreserve extends Card {

    public NaturalPreserve() {
        super("Natural Preserve", 9, Tags.SCIENCE_BUILDING, new OxygenRequirement(4, false));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.MONEY;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.NATURAL_PRESERVE, true);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Oxygen must be 4% or less");
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("Place tile next to no other tile");
    }
}
