package tm.corporation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.action.ResourceDeltaAction;

public class Thorgate extends Corporation {

    public Thorgate() {
        super("Thorgate", new Tags().power());
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new ResourceDeltaAction(new Resources(48)),
            new IncomeDeltaAction(new Resources(0, 0, 0, 0, 1, 0)));
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(new ActionChain(ActionType.ENERGY, "Energy income",
            new ResourceDeltaAction(new Resources(-8)),
            new IncomeDeltaAction(new Resources(0, 0, 0, 0, 1, 0))
        ));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("48 money", "1 energy income", "Everything with power tag costs 3 less");
    }
}
