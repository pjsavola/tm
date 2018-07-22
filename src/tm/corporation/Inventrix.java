package tm.corporation;

import java.util.Arrays;
import java.util.List;

import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.DrawCardsAction;
import tm.action.ResourceDeltaAction;

public class Inventrix extends Corporation {

    public Inventrix() {
        super("Inventrix", new Tags().science());
    }

    @Override
    public boolean start(final Game game) {
        if (super.start(game)) {
            game.getActionHandler().addPendingIrreversibleAction(new DrawCardsAction(3, false, false));
            return true;
        }
        return false;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ResourceDeltaAction(new Resources(45));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("45 money", "3 cards", "Has +/- 2 to card requirements");
    }
}
