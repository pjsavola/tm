package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.Card;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.AddWaterAction;
import tm.action.CardActionWithCost;

public class WaterImportFromEuropa extends Card {

    private final Action action = new CardActionWithCost(true, 12, true) {
        @Override
        public ActionType getType() {
            return ActionType.WATER_IMPORT_FROM_EUROPA;
        }

        @Override
        public boolean check(Game game) {
            return game.getPlanet().getWaterCount() > 0 && super.check(game);
        }

        @Override
        protected Action getAction(Game game) {
            return new AddWaterAction();
        }
    };

    public WaterImportFromEuropa() {
        super("Water Import From Europa", 25, new Tags().space().jovian(), false);
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "Pay 12 money to for an ocean", "(titanium may be used)", "", "1 VP for each jovian tag");
    }
}
