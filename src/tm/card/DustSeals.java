package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Tags;
import tm.requirement.OceanRequirement;

public class DustSeals extends Card {

    public DustSeals() {
        super("Dust Seals", 2, Tags.EMPTY, new OceanRequirement(3, false));
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires at most 3 ocean tiles");
    }
}
