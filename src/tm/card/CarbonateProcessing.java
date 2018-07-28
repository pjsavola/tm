package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class CarbonateProcessing extends Card {

    public CarbonateProcessing() {
        super("Carbonate Processing", 6, Tags.BUILDING);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(5, 0, 0, 0, -1, 3);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("-1 energy income", "3 heat income");
    }
}
