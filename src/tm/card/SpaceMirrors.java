package tm.card;

import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.action.CardAction;
import tm.action.CardActionWithCost;

public class SpaceMirrors extends Card {

    private final CardAction action = new CardActionWithCost(true, ActionType.SPACE_MIRRORS, new Resources(-7), Resources.ENERGY);

    public SpaceMirrors() {
        super("Space Mirrors", 3, Tags.SPACE.combine(Tags.POWER));
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }
}
