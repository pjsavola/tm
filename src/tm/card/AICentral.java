package tm.card;

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
import tm.requirement.TagRequirement;

public class AICentral extends Card {

    private final CardAction action = new CardAction(false, ActionType.AI_CENTRAL) {
        @Override
        protected Action getAction(Game game) {
            return new DrawCardsAction(2, false, false);
        }
    };

    public AICentral() {
        super("AI Central", 21, Tags.SCIENCE_BUILDING, new TagRequirement(Tags.SCIENCE_3));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.ENERGY.negate();
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 3 science tags");
    }
}
