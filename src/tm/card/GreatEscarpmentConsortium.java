package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.requirement.ProductionRequirement;

// Decrease any steel income by 1 is done from dummy player
public class GreatEscarpmentConsortium extends Card {

    public GreatEscarpmentConsortium() {
        super("Great Escarpment Cons.", 6, Tags.EMPTY, new ProductionRequirement(Resources.STEEL));
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.STEEL;
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires steel income");
    }
}
