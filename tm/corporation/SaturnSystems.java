package tm.corporation;

import java.awt.Graphics;

import javax.annotation.Nullable;
import tm.Card;
import tm.Corporation;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;
import tm.effect.PlayCardEffect;

public class SaturnSystems extends Corporation implements PlayCardEffect {

    public SaturnSystems() {
        super("Saturn Systems", Tags.JOVIAN, true);
    }

    @Override
    public Resources getResourceDelta() {
        return new Resources(42);
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.TITANIUM;
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        final int currentX = Tags.JOVIAN.render(g, x, y, true).x;
        Resources.MONEY.render(g, currentX + 4, y, true);
    }
    @Nullable
    @Override
    public Action cardPlayed(Card card) {
        if (card.getTags().has(Tags.Type.JOVIAN)) {
            return new IncomeDeltaAction(Resources.MONEY);
        }
        return null;
    }
}
