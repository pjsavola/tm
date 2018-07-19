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
import tm.action.CardAction;
import tm.action.ResourceDeltaAction;

public class MartianRail extends Card {

    private final Action action = new CardAction(true) {
        @Override
        protected Action getAction(Game game) {
            return new ResourceDeltaAction(new Resources(game.getCityCount()));
        }
    };

    public MartianRail() {
        super("Martian Rail", 13, new Tags().building(), false);
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(new ActionChain(
            ActionType.MARTIAN_RAIL,
            "Martian Rail",
            new ResourceDeltaAction(new Resources(0, 0, 0, 0, -1, 0)),
            action
        ));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "1 money for each city", "-1 energy");
    }
}
