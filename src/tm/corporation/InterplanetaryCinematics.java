package tm.corporation;

import tm.Corporation;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ResourceDeltaAction;

public class InterplanetaryCinematics extends Corporation {

    public InterplanetaryCinematics() {
        super("Interplanetary Cinematics", new Tags().building());
    }

    @Override
    protected Action getInitialAction() {
        return new ResourceDeltaAction(new Resources(30, 20, 0, 0, 0, 0));
    }
}
