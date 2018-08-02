package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.requirement.OceanRequirement;

// Decrease any heat income by 1 is done from dummy player
public class CloudSeeding extends Card {

    public CloudSeeding() {
        super("Cloud Seeding", 11, Tags.EMPTY, new OceanRequirement(3, true));
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(-1, 0, 0, 2, 0, 0);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 3 ocean tiles");
    }
}
