package tm.card;

import java.awt.Graphics;
import java.util.Collections;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.ImageCache;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.AddMarkerAction;
import tm.action.ResourceDeltaAction;
import tm.effect.PlayCardEffect;

public class ViralEnhancers extends Card implements PlayCardEffect {

    public ViralEnhancers() {
        super("Viral Enhancers", 9, Tags.SCIENCE.combine(Tags.MICROBE), null, true);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        g.drawImage(ImageCache.getImage("images/tag_plant.png"), x, y, null);
        g.drawImage(ImageCache.getImage("images/tag_animal.png"), x, y + 20, null);
        g.drawImage(ImageCache.getImage("images/tag_microbe.png"), x, y + 40, null);
        g.drawString("1 marker to", x + 24, y + 12);
        g.drawString("that card OR", x + 24, y + 32);
        Resources.PLANT.render(g,x + 24, y + 38, false);
    }

    @Nullable
    @Override
    public Action cardPlayed(Card card) {
        if (card.getTags().has(Tags.Type.ANIMAL) || card.getTags().has(Tags.Type.PLANT) || card.getTags().has(Tags.Type.MICROBE)) {
            return new AddMarkerAction(Collections.singletonList(card)) {
                @Override
                protected String getTitle() {
                    return "Select card for marker or gain 1 plant";
                }

                @Override
                protected Action onEmptySelection() {
                    return new ResourceDeltaAction(Resources.PLANT);
                }
            };
        }
        return null;
    }
}
