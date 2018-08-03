package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.requirement.TagRequirement;

public class GeneRepair extends Card {

    public GeneRepair() {
        super("Gene Repair", 12, Tags.SCIENCE, new TagRequirement(Tags.SCIENCE_3));
    }

    @Override
    public int getVPs() {
        return 2;
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(2);
    }
}
