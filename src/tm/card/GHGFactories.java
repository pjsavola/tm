package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class GHGFactories extends Card {

    public GHGFactories() {
        super("GHG Factories", 11, Tags.BUILDING);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(0, 0, 0, 0, -1, 4);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("-1 energy income", "4 heat income");
    }
}
