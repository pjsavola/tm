package tm.card;

import java.awt.Graphics;
import java.util.Collections;
import java.util.List;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.CardWithMarkers;
import tm.ImageCache;
import tm.Tags;
import tm.action.Action;
import tm.action.MarkerDeltaAction;
import tm.effect.PlayCardEffect;
import tm.requirement.OxygenRequirement;

public class Decomposers extends CardWithMarkers implements PlayCardEffect {

    public Decomposers() {
        super("Decomposers", 5, Tags.MICROBE, new OxygenRequirement(3, true), true, 1, 3);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Oxygen must be at least 3%");
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        g.drawImage(ImageCache.getImage("images/tag_plant.png"), x, y, null);
        g.drawImage(ImageCache.getImage("images/tag_animal.png"), x, y + 20, null);
        g.drawImage(ImageCache.getImage("images/tag_microbe.png"), x, y + 40, null);
        g.drawString("1 marker", x + 24, y + 32);
    }

    @Nullable
    @Override
    public Action cardPlayed(Card card) {
        int markers = 0;
        if (card.getTags().has(Tags.Type.ANIMAL)) {
            markers++;
        }
        if (card.getTags().has(Tags.Type.PLANT)) {
            markers++;
        }
        if (card.getTags().has(Tags.Type.MICROBE)) {
            markers++;
        }
        if (markers > 0) {
            return new MarkerDeltaAction(markers, this);
        }
        return null;
    }
}
