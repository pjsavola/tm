package tm.card;

import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.AddTerraformingRatingAction;
import tm.action.CardAction;
import tm.action.CardActionWithCost;
import tm.requirement.TemperatureRequirement;

public class CaretakerContract extends Card {

    private final CardAction action = new CardActionWithCost(true, ActionType.CARETAKER_CONTRACT, new Resources(0, 0, 0, 0, 0, -8)) {
        @Override
        protected Action getAction(Game game) {
            return new AddTerraformingRatingAction();
        }
    };

    public CaretakerContract() {
        super("Caretaker Contract", 3, Tags.EMPTY, new TemperatureRequirement(0, true));
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }
}
