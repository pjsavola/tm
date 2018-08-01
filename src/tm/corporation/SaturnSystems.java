package tm.corporation;

import java.awt.Graphics;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.Corporation;
import tm.Game;
import tm.ImageCache;
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
    public Resources getResourceDelta(Game game) {
        return new Resources(42);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return Resources.TITANIUM;
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        g.drawImage(ImageCache.getImage("images/tag_jovian.png"), x, y, null);
        Resources.MONEY.render(g, x + 24, y - 2, true);
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
