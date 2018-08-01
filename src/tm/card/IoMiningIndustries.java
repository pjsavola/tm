package tm.card;

import java.awt.Graphics;

import tm.Card;
import tm.Game;
import tm.Player;
import tm.Resources;
import tm.Tags;
import tm.effect.ScoringEffect;

public class IoMiningIndustries extends Card implements ScoringEffect {

    public IoMiningIndustries() {
        super("Io Mining Industries", 41, Tags.SPACE_JOVIAN);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(2, 0, 2, 0, 0, 0);
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
