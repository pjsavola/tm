package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class SolarWindPower extends Card {

    public SolarWindPower() {
        super("Solar Wind Power", 11, Tags.SCIENCE.combine(Tags.SPACE).combine(Tags.POWER));
    }

    @Override
    public Resources getResourceDelta(Game game) {
        return Resources.TITANIUM_2;
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return Resources.ENERGY;
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("2 titanium", "1 energy income");
    }
}
