package tm.corporation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tm.ActionType;
import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddOxygenAction;
import tm.action.IncomeDeltaAction;
import tm.action.PlaceTileAction;
import tm.action.ResourceDeltaAction;

public class Credicor extends Corporation {

    public Credicor() {
        super("Credicor", new Tags());
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ResourceDeltaAction(new Resources(57));
    }

    @Override
    public List<Action> getActions() {
        final List<Action> actions = new ArrayList<>(2);
        actions.add(new ActionChain(ActionType.GREENERY, "Greenery",
            new ResourceDeltaAction(new Resources(-23)),
            new PlaceTileAction(Tile.Type.GREENERY),
            new AddOxygenAction(),
            new ResourceDeltaAction(new Resources(4))
        ));
        actions.add(new ActionChain(ActionType.CITY, "City",
            new ResourceDeltaAction(new Resources(-25)),
            new PlaceTileAction(Tile.Type.CITY),
            new IncomeDeltaAction(new Resources(1)),
            new ResourceDeltaAction(new Resources(4))
        ));
        return actions;
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("57 money", "Get 4 money for value of 20 or more");
    }
}
