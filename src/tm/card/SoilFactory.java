package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class SoilFactory extends Card {

    public SoilFactory() {
        super("Soil Factory", 9, Tags.BUILDING);
    }

    @Override
    public int getVPs() {
        return 1;
    }
    
    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(new Resources(0, 0, 0, 1, -1, 0));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 plant income", "-1 energy income");
    }
}
