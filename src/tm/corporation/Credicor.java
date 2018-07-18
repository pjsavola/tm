package tm.corporation;

import java.util.Arrays;
import java.util.List;

import tm.Corporation;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ResourceDeltaAction;

public class Credicor extends Corporation {

    public Credicor() {
        super("Credicor", new Tags());
    }

    @Override
    protected Action getInitialAction() {
       	return new ResourceDeltaAction(new Resources(57));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("57 money", "Get 4 money for value of 20 or more");
    }
}
