package tm.corporation;

import java.util.Arrays;
import java.util.List;

import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.action.ResourceDeltaAction;

public class SaturnSystems extends Corporation {

    public SaturnSystems() {
        super("Saturn Systems", new Tags().jovian());
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new ResourceDeltaAction(new Resources(42)),
            new IncomeDeltaAction(new Resources(1, 0, 1, 0, 0, 0)));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("42 money", "1 titanium income", "1 money income for each Jovian tag");
    }
}
