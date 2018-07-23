package tm.corporation;

import java.util.Arrays;
import java.util.List;

import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ResourceDeltaAction;

public class InterplanetaryCinematics extends Corporation {

    public InterplanetaryCinematics() {
        super("Interplanetary Cinematics", Tags.BUILDING);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ResourceDeltaAction(new Resources(30, 20, 0, 0, 0, 0));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("30 money", "20 steel", "Get 2 money for each event you play");
    }
}
