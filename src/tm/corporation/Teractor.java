package tm.corporation;

import tm.Corporation;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ResourceDeltaAction;

// TODO: -3 discount for earth tag
public class Teractor extends Corporation {

    public Teractor() {
        super(new Tags().earth());
    }

    @Override
    protected Action getInitialAction() {
       	return new ResourceDeltaAction(new Resources(60));
    }

    @Override
    public String getTitle() {
        return "Teractor";
    }
}
