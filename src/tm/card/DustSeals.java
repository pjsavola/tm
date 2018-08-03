package tm.card;

import tm.Card;
import tm.Tags;
import tm.requirement.OceanRequirement;

public class DustSeals extends Card {

    public DustSeals() {
        super("Dust Seals", 2, Tags.EMPTY, new OceanRequirement(3, false));
    }

    @Override
    public int getVPs() {
        return 1;
    }
}
