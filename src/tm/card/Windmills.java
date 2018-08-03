package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.requirement.OxygenRequirement;

public class Windmills extends Card {

    public Windmills() {
        super("Windmills", 6, Tags.BUILDING_POWER, new OxygenRequirement(7, true));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.ENERGY;
    }
}
