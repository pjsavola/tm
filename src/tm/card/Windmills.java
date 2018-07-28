package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.requirement.OxygenRequirement;

public class Windmills extends Card {

    public Windmills() {
        super("Windmills", 6, Tags.BUILDING_POWER, new OxygenRequirement(7, true));
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
        return Collections.singletonList("Requires at least 7% oxygen");
    }
}
