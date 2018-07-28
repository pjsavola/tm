package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.requirement.OxygenRequirement;

public class Insects extends Card {

    public Insects() {
        super("Insects", 9, Tags.MICROBE, new OxygenRequirement(6, true));
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(0, 0, 0, game.getCurrentPlayer().getTags().getCount(Tags.Type.PLANT), 0, 0);
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
