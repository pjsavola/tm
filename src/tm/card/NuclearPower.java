package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class NuclearPower extends Card {

    public NuclearPower() {
        super("Nuclear Power", 10, new Tags().building().power());
    }

    @Override
    public Action getInitialAction() {
        return new IncomeDeltaAction(new Resources(-2, 0, 0, 0, 3, 0));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("-2 money income", "3 energy income");
    }
}
