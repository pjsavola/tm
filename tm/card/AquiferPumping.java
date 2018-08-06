package tm.card;

import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.AddWaterAction;
import tm.action.CardAction;
import tm.action.CardActionWithCost;

public class AquiferPumping extends Card {

    private final CardAction action = new CardActionWithCost(true, ActionType.AQUIFER_PUMPING, new Resources(-8), Resources.EMPTY, true, false) {
        @Override
        public boolean check(Game game) {
            return game.getPlanet().getWaterRemaining() > 0 && super.check(game);
        }

        @Override
        protected Action getAction(Game game) {
            return new AddWaterAction();
        }
    };

    public AquiferPumping() {
        super("Aquifer Pumping", 18, Tags.BUILDING);
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }
}
