package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.requirement.TemperatureRequirement;

public class DesignedMicroorganisms extends Card {

    public DesignedMicroorganisms() {
        super("Designed Microorganisms", 16, Tags.SCIENCE.combine(Tags.MICROBE), new TemperatureRequirement(-14, false));
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return Resources.PLANT_2;
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("It must be -14C or colder");
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("2 plant income");
    }
}
