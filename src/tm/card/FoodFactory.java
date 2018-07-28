package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class FoodFactory extends Card {

    public FoodFactory() {
        super("Food Factory", 12, Tags.BUILDING);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(4, 0, 0, -1, 0, 0);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("4 money income", "-1 plant income");
    }
}
