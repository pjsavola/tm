package tm.corporation;

import java.awt.Color;
import java.awt.Graphics;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ResourceDeltaAction;
import tm.effect.PlayCardEffect;

public class Credicor extends Corporation implements PlayCardEffect {

    public Credicor() {
        super("Credicor", Tags.EMPTY, true);
    }

    @Override
    public Resources getResourceDelta(Game game) {
        return new Resources(57);
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        g.setColor(new Color(0xFFFF00));
        final String str = ">= 20";
        g.drawString(str, x, y + 12);
        new Resources(4).render(g, x + g.getFontMetrics().stringWidth(str) + 5, y - 2, false);
    }

    @Nullable
    @Override
    public Action cardPlayed(Card card) {
        if (card.getCost() >= 20) {
            return new ResourceDeltaAction(new Resources(4));
        }
        return null;
    }
}
