package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.requirement.OceanRequirement;

public class WavePower extends Card {

    public WavePower() {
        super("Wave Power", 8, Tags.POWER, new OceanRequirement(3, true));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return Resources.ENERGY;
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 3 ocean tiles");
    }
}
