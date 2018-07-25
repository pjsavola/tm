package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class TropicalResort extends Card {

    public TropicalResort() {
        super("Tropical Resort", 13, Tags.BUILDING);
    }

    @Override
    public int getVPs() {
        return 2;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(new Resources(3, 0, 0, 0, 0, -2));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("3 money income", "-2 heat income");
    }
}
