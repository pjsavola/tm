package tm.card;

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
}
