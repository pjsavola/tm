package tm.corporation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.Corporation;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddOxygenAction;
import tm.action.IncomeDeltaAction;
import tm.action.PlaceTileAction;
import tm.action.ResourceDeltaAction;

public class Ecoline extends Corporation {

    public Ecoline() {
        super("Ecoline", new Tags().plant());
    }

    @Override
    public Action getInitialAction() {
       	return new ActionChain(
       		new ResourceDeltaAction(new Resources(36, 0, 0, 3, 0, 0)),
       		new IncomeDeltaAction(new Resources(0, 0, 0, 2, 0, 0)));
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(new ActionChain(ActionType.PLANT_TO_GREENERY, "Plant to greenery",
            new ResourceDeltaAction(new Resources(0, 0, 0, -7, 0, 0)),
            new PlaceTileAction(Tile.Type.GREENERY),
            new AddOxygenAction()
        ));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("36 money", "3 plants", "2 plant income", "Greenery costs 7 plants");
    }
}
