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

public class Helion extends Corporation {

    public Helion() {
        super("Helion", Tags.SPACE);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new ResourceDeltaAction(new Resources(42)),
            new IncomeDeltaAction(new Resources(0, 0, 0, 0, 0, 3)));
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(new ActionChain(ActionType.HEAT_TO_MONEY, "Heat to money",
            new ResourceDeltaAction(new Resources(1, 0, 0, 0, 0, -1))
        ));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("42 money", "3 heat income", "May convert heat to money");
    }
}
