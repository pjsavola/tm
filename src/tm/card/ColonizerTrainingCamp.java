package tm.card;

import java.util.Collections;
import java.util.List;

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

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Oxygen must be 5% or less");
    }
}
