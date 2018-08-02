package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Resources;
import tm.Tags;
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
    public Resources getIncomeDelta() {
        return Resources.TITANIUM;
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires titanium income");
    }
}
