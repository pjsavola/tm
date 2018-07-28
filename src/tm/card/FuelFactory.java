package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class FuelFactory extends Card {

    public FuelFactory() {
        super("Fuel Factory", 6, Tags.BUILDING);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(1, 0, 1, 0, -1, 0);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 money income", "1 titanium income", "-1 energy income");
    }
}
