package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.AddTerraformingRatingAction;
import tm.action.CardActionWithCost;

public class EquatorialMagnetizer extends Card {

    private final Action action = new CardActionWithCost(true, ActionType.EQUATORIAL_MAGNETIZER, Resources.EMPTY, Resources.ENERGY.negate()) {
        @Override
        protected Action getAction(Game game) {
            return new AddTerraformingRatingAction();
        }
    };

    public EquatorialMagnetizer() {
        super("Equatorial Magnetizer", 11, Tags.BUILDING);
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "-1 energy income", "1 TR");
    }
}
