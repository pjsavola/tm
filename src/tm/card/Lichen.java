package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;
import tm.requirement.TemperatureRequirement;

public class Lichen extends Card {

    public Lichen() {
        super("Lichen", 7, Tags.PLANT, new TemperatureRequirement(-24, true));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(Resources.PLANT);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires -24C or warmer");
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 plant income");
    }
}
