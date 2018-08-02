package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.requirement.OceanRequirement;

public class Moss extends Card {

    public Moss() {
        super("Moss", 4, Tags.PLANT, new OceanRequirement(3, true));
    }

    @Override
    public Resources getResourceDelta() {
        return Resources.PLANT.negate();
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.PLANT;
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 3 ocean tiles");
    }
}
