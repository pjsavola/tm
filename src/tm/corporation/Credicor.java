package tm.corporation;

import java.awt.Color;
import java.awt.Graphics;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.Corporation;
import tm.Renderer;
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
    public Resources getResourceDelta() {
        return new Resources(57);
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        g.setColor(new Color(0xFFFF00));
        final int newX = Renderer.renderText(g, "≥ 20", x, y + 4, false).x;
        Resources.MONEY_4.render(g, newX + 4, y, false);
    }

    @Nullable
    @Override
    public Action cardPlayed(Card card) {
        if (card.getCost() >= 20) {
            return new ResourceDeltaAction(Resources.MONEY_4);
        }
        return null;
    }
}
