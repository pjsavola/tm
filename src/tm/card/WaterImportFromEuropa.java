package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.Card;
import tm.Game;
import tm.Player;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.AddWaterAction;
import tm.action.CardActionWithCost;
import tm.effect.ScoringEffect;

public class WaterImportFromEuropa extends Card implements ScoringEffect {

    private final Action action = new CardActionWithCost(true, ActionType.WATER_IMPORT_FROM_EUROPA, new Resources(-12), Resources.EMPTY, true) {
        @Override
        public boolean check(Game game) {
            return game.getPlanet().getWaterRemaining() > 0 && super.check(game);
        }

        @Override
        protected Action getAction(Game game) {
            return new AddWaterAction();
        }
    };

    public WaterImportFromEuropa() {
        super("Water Import From Europa", 25, Tags.SPACE.combine(Tags.JOVIAN));
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "Pay 12 money to for an ocean", "(titanium may be used)", "", "1 VP for each jovian tag");
    }

    @Override
    public int getVPs(Player player) {
        return player.getTags().getCount(Tags.Type.JOVIAN);
    }
}
