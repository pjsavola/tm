package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class IndustrialMicrobes extends Card {

    public IndustrialMicrobes() {
        super("Industrial Microbes", 12, Tags.BUILDING.combine(Tags.MICROBE));
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(0, 1, 0, 0, 1, 0);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 steel income", "1 energy income");
    }
}
