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

public class Insects extends Card {

    public Insects() {
        super("Insects", 8, Tags.MICROBE, new OxygenRequirement(6, true));
    }

    @Override
    public Action getInitialAction(Game game) {
        final int plantCount = game.getCurrentPlayer().getTags().getCount(Tags.Type.PLANT);
        return new IncomeDeltaAction(new Resources(0, 0, 0, plantCount, 0, 0));
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 6% oxygen");
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 plant income for each plant tag");
    }
}
