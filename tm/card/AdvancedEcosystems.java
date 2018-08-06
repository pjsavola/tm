package tm.card;

import tm.Card;
import tm.Tags;
import tm.requirement.TagRequirement;

public class AdvancedEcosystems extends Card {

    private static final Tags tags = Tags.ANIMAL.combine(Tags.MICROBE).combine(Tags.PLANT);

    public AdvancedEcosystems() {
        super("Advanced Ecosystems", 11, tags, new TagRequirement(tags));
    }

    @Override
    public int getVPs() {
        return 3;
    }
}
