package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.requirement.OxygenRequirement;

// Plant income is removed from dummy player
public class BiomassCombustors extends Card {

    public BiomassCombustors() {
        super("Biomass Combustors", 4, Tags.BUILDING_POWER, new OxygenRequirement(6, true));
    }

    @Override
    public int getVPs() {
        return -1;
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(0, 0, 0, 0, 2, 0);
    }
}
