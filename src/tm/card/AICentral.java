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
import tm.action.CardAction;
import tm.action.DrawCardsAction;
import tm.action.IncomeDeltaAction;
import tm.requirement.TagRequirement;

public class AICentral extends Card {

    private final Action action = new CardAction(false, ActionType.AI_CENTRAL) {
        @Override
        protected Action getAction(Game game) {
            return new DrawCardsAction(2, false, false);
        }
    };

    public AICentral() {
        super("AI Central", 21, Tags.SCIENCE.combine(Tags.BUILDING), new TagRequirement(Tags.Type.SCIENCE.createTags(3)));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(Resources.ENERGY.negate());
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 3 science tags");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "Draw 2 cards", "", "-1 energy income");
    }
}
