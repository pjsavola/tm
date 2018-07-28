package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
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
    public Resources getResourceDelta(Game game) {
        return Resources.PLANT;
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return Resources.PLANT_3;
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires -4C or warmer");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 plant", "3 plant income");
    }
}
