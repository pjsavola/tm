package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.requirement.OceanRequirement;

public class NitrophilicMoss extends Card {

    public NitrophilicMoss() {
        super("Nitrophilic Moss", 8, Tags.PLANT, new OceanRequirement(3, true));
    }

    @Override
    public Resources getResourceDelta() {
        return Resources.PLANT_2.negate();
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.PLANT_2;
    }
}
