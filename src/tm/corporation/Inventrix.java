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
import tm.effect.RequirementEffect;

public class Inventrix extends Corporation implements RequirementEffect {

    public Inventrix() {
        super("Inventrix", Tags.SCIENCE);
    }

    @Override
    public boolean start(final Game game) {
        if (super.start(game)) {
            game.getActionHandler().addPendingAction(new DrawCardsAction(3, false, false));
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

    @Override
    public int getTolerance() {
        return 2;
    }
}
