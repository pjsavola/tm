package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.requirement.OxygenRequirement;

public class MethaneFromTitan extends Card {

    public MethaneFromTitan() {
        super("Methane From Titan", 28, Tags.SPACE_JOVIAN, new OxygenRequirement(2, true));
    }

    @Override
    public int getVPs() {
        return 2;
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(0, 0, 0, 2, 0, 2);
    }
}
