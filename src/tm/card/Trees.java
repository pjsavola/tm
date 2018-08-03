package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.requirement.TemperatureRequirement;

public class Trees extends Card {

    public Trees() {
        super("Trees", 13, Tags.PLANT, new TemperatureRequirement(-4, true));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Resources getResourceDelta() {
        return Resources.PLANT;
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.PLANT_3;
    }
}
