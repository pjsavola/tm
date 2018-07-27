package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Tags;
import tm.requirement.OxygenRequirement;

public class BreathingFilters extends Card {

    public BreathingFilters() {
        super("Breathing Filters", 11, Tags.SCIENCE, new OxygenRequirement(7, true));
    }

    @Override
    public int getVPs() {
        return 2;
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Oxygen must be at least 7%");
    }
}
