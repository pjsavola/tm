package tm.card;

import java.awt.Graphics;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Player;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.AddWaterAction;
import tm.action.CardAction;
import tm.action.CardActionWithCost;
import tm.effect.ScoringEffect;

public class WaterImportFromEuropa extends Card implements ScoringEffect {

    private final CardAction action = new CardActionWithCost(true, "-12 money (titanium), 1 ocean", new Resources(-12), Resources.EMPTY, false, true) {
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
        super("Water Import From Europa", 25, Tags.SPACE_JOVIAN);
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    public int getVPs(Player player) {
        return player.getTags().getCount(Tags.Type.JOVIAN);
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        g.drawString("1 vp for each", x, y + 12);
        g.drawString("jovian tag", x, y + 28);
    }
}
