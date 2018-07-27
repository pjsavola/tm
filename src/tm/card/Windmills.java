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

public class Windmills extends Card {

    public Windmills() {
        super("Windmills", 6, Tags.BUILDING.combine(Tags.POWER), new OxygenRequirement(7, true));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(Resources.ENERGY);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires at least 7% oxygen");
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 energy income");
    }
}
