package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.AddTerraformingRatingAction;
import tm.action.CardAction;
import tm.action.CardActionWithCost;

public class EquatorialMagnetizer extends Card {

    private final CardAction action = new CardActionWithCost(true, "-1 energy inome, 1 TR", Resources.EMPTY, Resources.ENERGY.negate()) {
        @Override
        protected Action getAction(Game game) {
            return new AddTerraformingRatingAction();
        }
    };

    public EquatorialMagnetizer() {
        super("Equatorial Magnetizer", 11, Tags.BUILDING);
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }
}
