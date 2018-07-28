package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.requirement.TemperatureRequirement;

public class Heather extends Card {

    public Heather() {
        super("Heather", 6, Tags.PLANT, new TemperatureRequirement(-14, true));
    }

    @Override
    public Resources getResourceDelta(Game game) {
        return Resources.PLANT;
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return Resources.PLANT;
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires -14C or warmer");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 plant", "1 plant income");
    }
}
