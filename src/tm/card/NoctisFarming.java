package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.requirement.TemperatureRequirement;

public class NoctisFarming extends Card {

    public NoctisFarming() {
        super("Noctis Farming", 10, Tags.BUILDING.combine(Tags.PLANT), new TemperatureRequirement(-20, true));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Resources getResourceDelta(Game game) {
        return Resources.PLANT_2;
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return Resources.MONEY;
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires -20C or warmer");
    }
}
