package tm.corporation;

import java.util.Collections;
import java.util.List;

import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.AddTerraformingRatingAction;
import tm.action.CardAction;
import tm.action.CardActionWithCost;

public class UnitedNationsMarsInitiative extends Corporation {

    private final CardAction action = new CardActionWithCost(true, "1 TR", new Resources(-3)) {
        @Override
        public boolean check(Game game) {
            return game.getCurrentPlayer().hasIncreasedRating();
        }

        @Override
        protected Action getAction(Game game) {
            return new AddTerraformingRatingAction();
        }
    };

    public UnitedNationsMarsInitiative() {
        super("United Nations Mars Initiative", Tags.PLANT);
    }

    @Override
    public Resources getResourceDelta() {
        return new Resources(40);
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }
}
