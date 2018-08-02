package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.requirement.TemperatureRequirement;

public class Lichen extends Card {

    public Lichen() {
        super("Lichen", 7, Tags.PLANT, new TemperatureRequirement(-24, true));
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.PLANT;
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires -24C or warmer");
    }
}
