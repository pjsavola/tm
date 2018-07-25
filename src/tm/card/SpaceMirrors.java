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
import tm.action.CardActionWithCost;
import tm.action.IncomeDeltaAction;

public class SpaceMirrors extends Card {

    private final Action action = new CardActionWithCost(true, 7, false) {
        @Override
        public ActionType getType() {
            return ActionType.SPACE_MIRRORS;
        }

        @Override
        protected Action getAction(Game game) {
            return new IncomeDeltaAction(Resources.ENERGY);
        }
    };

    public SpaceMirrors() {
        super("Space Mirrors", 3, Tags.SPACE.combine(Tags.POWER));
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "-7 money", "1 energy income");
    }
}
