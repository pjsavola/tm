package tm.corporation;

import java.util.Arrays;
import java.util.List;

import tm.Corporation;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.action.ResourceDeltaAction;

public class MiningGuild extends Corporation {

    public MiningGuild() {
        super("Mining Guild", new Tags().building().building());
    }

    @Override
    public Action getInitialAction() {
        return new ActionChain(
        	new ResourceDeltaAction(new Resources(30, 5, 0, 0, 0, 0)),
        	new IncomeDeltaAction(new Resources(0, 1, 0, 0, 0, 0)));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("30 money", "5 steel", "1 steel income", "Get 1 steel income for covering steel or titanium");
    }
}
