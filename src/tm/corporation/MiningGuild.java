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

public class MiningGuild extends Corporation {

    public MiningGuild() {
        super("Mining Guild", Tags.Type.BUILDING.createTags(2));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new ResourceDeltaAction(new Resources(30, 5, 0, 0, 0, 0)),
            new IncomeDeltaAction(Resources.STEEL)
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("30 money", "5 steel", "1 steel income", "Get 1 steel income for covering steel or titanium");
    }
}
