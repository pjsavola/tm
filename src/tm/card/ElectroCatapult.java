package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.Card;
import tm.Game;
import tm.Planet;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.CardAction;
import tm.action.CardActionWithCost;
import tm.action.ResourceDeltaAction;

public class ElectroCatapult extends Card {

    private final CardAction action1 = new CardActionWithCost(true, Resources.STEEL.negate()) {
        @Override
        public ActionType getType() {
            return ActionType.ELECTRO_CATAPULT_1;
        }

        @Override
        protected Action getAction(Game game) {
            return new ResourceDeltaAction(new Resources(7));
        }
    };

    private final CardAction action2 = new CardActionWithCost(true, Resources.PLANT.negate()) {
        @Override
        public ActionType getType() {
            return ActionType.ELECTRO_CATAPULT_2;
        }

        @Override
        protected Action getAction(Game game) {
            return new ResourceDeltaAction(new Resources(7));
        }
    };

    public ElectroCatapult() {
        super("Electro Catapult", 17, Tags.BUILDING);
        action1.setAlternativeAction(action2);
        action2.setAlternativeAction(action1);
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getOxygen() <= 8 + tolerance;
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ResourceDeltaAction(Resources.ENERGY.negate());
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
        return Arrays.asList("Action 1:", "7 money, -1 steel", "", "Action 2:", "7 money, -1 plant", "", "-1 energy income");
    }
}
