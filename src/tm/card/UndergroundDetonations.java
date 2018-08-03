package tm.card;

import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.action.CardAction;
import tm.action.CardActionWithCost;

public class UndergroundDetonations extends Card {

    private final CardAction action = new CardActionWithCost(true, ActionType.UNDERGROUND_DETONATIONS, new Resources(-10), new Resources(0, 0, 0, 0, 0, 2));

    public UndergroundDetonations() {
        super("Underground Detonations", 6, Tags.BUILDING);
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }
}
