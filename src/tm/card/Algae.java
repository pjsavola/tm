package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.requirement.OceanRequirement;

public class Algae extends Card {

    public Algae() {
        super("Algae", 10, Tags.PLANT, new OceanRequirement(5, true));
    }

    @Override
    public Resources getResourceDelta() {
        return Resources.PLANT;
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.PLANT_2;
    }
}
