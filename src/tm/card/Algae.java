package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.requirement.OceanRequirement;

public class Algae extends Card {

    public Algae() {
        super("Algae", 10, Tags.PLANT, new OceanRequirement(5, true));
    }

    @Override
    public Resources getResourceDelta(Game game) {
        return Resources.PLANT;
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return Resources.PLANT_2;
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 5 ocean tiles");
    }
}
