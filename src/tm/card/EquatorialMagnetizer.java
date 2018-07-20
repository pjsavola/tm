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
import tm.action.ActionChain;
import tm.action.AddTerraformingRatingAction;
import tm.action.CardAction;
import tm.action.IncomeDeltaAction;

public class EquatorialMagnetizer extends Card {

    private final Action action = new CardAction(true) {
        @Override
        protected Action getAction(Game game) {
            return new AddTerraformingRatingAction();
        }
    };

    public EquatorialMagnetizer() {
        super("Equatorial Magnetizer", 11, new Tags().building(), false);
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(new ActionChain(
            ActionType.EQUATORIAL_MAGNETIZER,
            "Equatorial Magnetizer",
            new IncomeDeltaAction(new Resources(0, 0, 0, 0, -1, 0)),
            action
        ));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "-1 energy income", "1 TR");
    }
}
