package tm.corporation;

import java.util.Arrays;
import java.util.List;

import tm.Corporation;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.action.PlaceTileAction;
import tm.action.ResourceDeltaAction;

public class TharsisRepublic extends Corporation {

    public TharsisRepublic() {
        super("Tharsis Republic", new Tags().building());
    }

    @Override
    protected Action getInitialAction() {
        return new ActionChain(
            new ResourceDeltaAction(new Resources(40)),
            new IncomeDeltaAction(new Resources(2)), // Initial cities in solo game
            new PlaceTileAction(Tile.Type.CITY));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("40 money", "3 money for each city you play", "1 money income for each city");
    }
}
