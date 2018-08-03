package tm.card;

import tm.Card;
import tm.Tags;
import tm.requirement.OxygenRequirement;

public class ColonizerTrainingCamp extends Card {

    public ColonizerTrainingCamp() {
        super("Colonizer Training Camp", 8, Tags.BUILDING.combine(Tags.JOVIAN), new OxygenRequirement(5, false));
    }

    @Override
    public int getVPs() {
        return 2;
    }
}
