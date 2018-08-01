package tm.card;

import java.awt.Graphics;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.ImageCache;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ResourceDeltaAction;
import tm.effect.PlayCardEffect;

public class OptimalAerobraking extends Card implements PlayCardEffect {

    public OptimalAerobraking() {
        super("Optimal Aerobraking", 7, Tags.SPACE, null, true);
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        g.drawImage(ImageCache.getImage("images/tag_event.png"), x, y + 8, null);
        g.drawImage(ImageCache.getImage("images/tag_space.png"), x + 18, y + 8, null);
        //new Resources(3).render(g, x + 42, y - 2, false);
        new Resources(3, 0, 0, 0, 0,3).render(g, x + 42, y - 2, false);
    }

    @Nullable
    @Override
    public Action cardPlayed(Card card) {
        if (card.getTags().has(Tags.Type.EVENT) && card.getTags().has(Tags.Type.SPACE)) {
            return new ResourceDeltaAction(new Resources(3, 0, 0, 0, 0, 3));
        }
        return null;
    }
}
