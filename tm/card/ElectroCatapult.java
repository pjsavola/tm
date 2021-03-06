package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.action.CardAction;
import tm.action.CardActionWithCost;
import tm.requirement.OxygenRequirement;

public class ElectroCatapult extends Card {

    private final CardAction action1 = new CardActionWithCost(true, "7 money, -1 steel", new Resources(7, -1, 0, 0, 0, 0));

    private final CardAction action2 = new CardActionWithCost(true, "7 money, -1 plant", new Resources(7, 0, 0, -1, 0, 0));

    public ElectroCatapult() {
        super("Electro Catapult", 17, Tags.BUILDING, new OxygenRequirement(8, false));
        action1.setAlternativeAction(action2);
        action2.setAlternativeAction(action1);
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
        return Arrays.asList(action1, action2);
    }
}
