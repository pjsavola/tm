package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.requirement.TemperatureRequirement;

public class Heather extends Card {

    public Heather() {
        super("Heather", 6, Tags.PLANT, new TemperatureRequirement(-14, true));
    }

    @Override
    public Resources getResourceDelta() {
        return Resources.PLANT;
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.PLANT;
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires -14C or warmer");
    }
}
