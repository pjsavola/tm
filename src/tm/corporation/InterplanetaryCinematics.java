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
import tm.action.ResourceDeltaAction;
import tm.effect.PlayCardEffect;

public class InterplanetaryCinematics extends Corporation implements PlayCardEffect {

    public InterplanetaryCinematics() {
        super("Interplanetary Cinematics", Tags.BUILDING, true);
    }

    @Override
    public Resources getResourceDelta(Game game) {
        return new Resources(30, 20, 0, 0, 0, 0);
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        g.drawImage(ImageCache.getImage("images/tag_event.png"), x, y, null);
        new Resources(2).render(g, x + 24, y - 2, false);
    }

    @Nullable
    @Override
    public Action cardPlayed(Card card) {
        if (card.getTags().has(Tags.Type.EVENT)) {
            return new ResourceDeltaAction(new Resources(2));
        }
        return null;
    }
}
