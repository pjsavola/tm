package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.CardAction;
import tm.action.IncomeDeltaAction;
import tm.action.ResourceDeltaAction;

public class SpaceMirrors extends Card {

    private final Action action = new CardAction(true) {
        @Override
        protected Action getAction(Game game) {
            return new ActionChain(
                new ResourceDeltaAction(new Resources(-5)),
                new IncomeDeltaAction(Resources.ENERGY)
            );
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
