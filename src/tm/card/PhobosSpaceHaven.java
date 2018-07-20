package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

// TODO: What everything is affected by cities outside mars vs. inside mars?
public class PhobosSpaceHaven extends Card {

    public PhobosSpaceHaven() {
        super("Phobos Space Haven", 25, new Tags().space().city());
    }

    @Override
    public int getVPs() {
        return 2;
    }

    @Override
    public Action getInitialAction() {
        return new IncomeDeltaAction(new Resources(0, 0, 1, 0, 0, 0));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 titanium income", "City to Phobos");
    }
}
