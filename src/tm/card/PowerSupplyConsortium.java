package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Player;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

// Remove 1 energy income is done from dummy opponent
public class PowerSupplyConsortium extends Card {

    public PowerSupplyConsortium() {
        super("Power Supply Consotrium", 5, Tags.POWER);
    }

    @Override
    public boolean check(Player player) {
        return player.getTags().hasAll(Tags.Type.POWER.createTags(2));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(Resources.ENERGY);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 2 power tags");
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 energy income");
    }
}
