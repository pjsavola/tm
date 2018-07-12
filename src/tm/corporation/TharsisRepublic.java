package tm.corporation;

import tm.Corporation;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.action.PlaceTileAction;
import tm.action.ResourceDeltaAction;

// TODO: +1 income and 3 money per city
public class TharsisRepublic extends Corporation {

    public TharsisRepublic() {
        super("Tharsis Republic", new Tags().building());
    }

    @Override
    protected Action getInitialAction() {
        return new ActionChain(
            new ResourceDeltaAction(new Resources(43)),
            new IncomeDeltaAction(new Resources(3)),
            new PlaceTileAction(Tile.Type.CITY));
    }
}
