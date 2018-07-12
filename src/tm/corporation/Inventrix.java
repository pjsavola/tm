package tm.corporation;

import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.DrawCardsAction;
import tm.action.ResourceDeltaAction;

// TODO: +/-2 requirements
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
    protected Action getInitialAction() {
        return new ResourceDeltaAction(new Resources(45));
    }
}
