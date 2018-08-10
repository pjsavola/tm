package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.action.CardAction;
import tm.action.CardActionWithCost;

public class SpaceElevator extends Card {

    private final CardAction action = new CardActionWithCost(true, "5 money, -1 steel", new Resources(5, -1, 0, 0, 0, 0));

    public SpaceElevator() {
        super("Space Elevator", 27, Tags.SPACE.combine(Tags.BUILDING));
    }

    @Override
    public int getVPs() {
        return 2;
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.TITANIUM;
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }
}
