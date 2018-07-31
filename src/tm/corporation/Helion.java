package tm.corporation;

import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.ResourceDeltaAction;

public class Helion extends Corporation {

    public Helion() {
        super("Helion", Tags.SPACE);
    }

    @Override
    public Resources getResourceDelta(Game game) {
        return new Resources(42);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(0, 0, 0, 0, 0, 3);
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(new ActionChain(ActionType.HEAT_TO_MONEY, "Heat to money",
            new ResourceDeltaAction(new Resources(1, 0, 0, 0, 0, -1))
        ));
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("May convert heat to money");
    }
}
