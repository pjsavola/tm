package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;
import tm.requirement.ProductionRequirement;

// Decrease any titanium income by 1 is done from dummy player
public class AsteroidMiningConsortium extends Card {

    public AsteroidMiningConsortium() {
        super("Asteroid Mining Cons.", 13, Tags.JOVIAN, new ProductionRequirement(Resources.TITANIUM));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(Resources.TITANIUM);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires titanium income");
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 titanium income");
    }
}
