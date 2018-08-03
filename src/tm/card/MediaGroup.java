package tm.card;

import java.awt.Color;
import java.awt.Graphics;

import tm.Card;
import tm.ImageCache;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ResourceDeltaAction;
import tm.effect.PlayCardEffect;

public class MediaGroup extends Card implements PlayCardEffect {

    public MediaGroup() {
        super("Media Group", 6, Tags.EARTH, null, true);
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        g.drawImage(ImageCache.getImage("images/tag_event.png"), x, y, null);
        g.setColor(new Color(0xFFFF00));
        final String str = "3";
        g.drawString(str, x + 24, y + 12);
        Resources.MONEY_3.render(g, x + g.getFontMetrics().stringWidth(str) + 29, y, false);
    }

    @Override
    public Action cardPlayed(Card card) {
        if (card.getTags().has(Tags.Type.EVENT)) {
            return new ResourceDeltaAction(new Resources(3));
        }
        return null;
    }
}
