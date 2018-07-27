package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;
import tm.requirement.OxygenRequirement;

// Plant income is removed from dummy player
public class BiomassCombustors extends Card {

    public BiomassCombustors() {
        super("Biomass Combustors", 4, Tags.BUILDING_POWER, new OxygenRequirement(6, true));
    }

    @Override
    public int getVPs() {
        return -1;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(new Resources(0, 0, 0, 0, 2, 0));
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires at least 6% oxygen");
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("2 energy income");
    }
}
