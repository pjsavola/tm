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
import tm.action.CardActionWithCost;
import tm.action.ResourceDeltaAction;
import tm.requirement.OxygenRequirement;

public class ElectroCatapult extends Card {

    private final CardAction action1 = new CardActionWithCost(true, ActionType.ELECTRO_CATAPULT_1, Resources.STEEL.negate()) {
        @Override
        protected Action getAction(Game game) {
            return new ResourceDeltaAction(new Resources(7));
        }
    };

    private final CardAction action2 = new CardActionWithCost(true, ActionType.ELECTRO_CATAPULT_2, Resources.PLANT.negate()) {
        @Override
        protected Action getAction(Game game) {
            return new ResourceDeltaAction(new Resources(7));
        }
    };

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
    public Resources getIncomeDelta(Game game) {
        return Resources.ENERGY.negate();
    }

    @Override
    public List<Action> getActions() {
        return Arrays.asList(action1, action2);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Oxygen must be 8% or less");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action 1:", "7 money, -1 steel", "", "Action 2:", "7 money, -1 plant");
    }
}
