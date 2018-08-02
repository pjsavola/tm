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

public class DomedCrater extends Card {

    public DomedCrater() {
        super("Domed Crater", 24, Tags.BUILDING_CITY, new OxygenRequirement(7, false));
    }

    @Override
    public Resources getResourceDelta() {
        return Resources.PLANT_3;
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(3, 0, 0, 0, -1, 0);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.CITY);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Oxygen must be 7% or less");
    }
}
