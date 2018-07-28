package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class NuclearPower extends Card {

    public NuclearPower() {
        super("Nuclear Power", 10, Tags.BUILDING_POWER);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(-2, 0, 0, 0, 3, 0);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("-2 money income", "3 energy income");
    }
}
