package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class CarbonateProcessing extends Card {

    public CarbonateProcessing() {
        super("Carbonate Processing", 6, new Tags().building());
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(new Resources(0, 0, 0, 0, -1, 4));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("-1 energy income", "4 heat income");
    }
}
