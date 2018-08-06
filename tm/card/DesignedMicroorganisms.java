package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.requirement.TemperatureRequirement;

public class DesignedMicroorganisms extends Card {

    public DesignedMicroorganisms() {
        super("Designed Microorganisms", 16, Tags.SCIENCE.combine(Tags.MICROBE), new TemperatureRequirement(-14, false));
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.PLANT_2;
    }
}
