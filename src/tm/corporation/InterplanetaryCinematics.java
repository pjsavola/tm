package tm.corporation;

import tm.Corporation;
import tm.Resources;
import tm.Tags;

// TODO: +2 money for each event
public class InterplanetaryCinematics extends Corporation {

    public InterplanetaryCinematics() {
        super(new Tags().building());
    }

    @Override
    public Resources getInitialResources() {
        return new Resources(30, 20, 0, 0, 0, 0);
    }

    @Override
    public String getTitle() {
        return "Interplanetary Cinematics";
    }
}
