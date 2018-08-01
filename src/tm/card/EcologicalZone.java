package tm.card;

import java.awt.Graphics;
import java.util.Collections;
import java.util.List;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.CardWithMarkers;
import tm.Game;
import tm.ImageCache;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.MarkerDeltaAction;
import tm.action.PlaceTileAction;
import tm.effect.PlayCardEffect;
import tm.requirement.GreeneryRequirement;

public class EcologicalZone extends CardWithMarkers implements PlayCardEffect {

    public EcologicalZone() {
        super("Ecological Zone", 12, Tags.PLANT.combine(Tags.ANIMAL), new GreeneryRequirement(), true);
    }

    @Override
    public int getVPs() {
        return getMarkerCount() / 2;
    }

    @Override
    public String getRatio() {
        return "1:2";
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.ECOLOGICAL_ZONE);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Must have greenery tile, place next to greenery tile");
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        g.drawImage(ImageCache.getImage("images/tag_plant.png"), x, y, null);
        g.drawImage(ImageCache.getImage("images/tag_animal.png"), x, y + 20, null);
        g.drawString("1 marker", x + 24, y + 22);
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
        if (markers > 0) {
            return new MarkerDeltaAction(markers, this);
        }
        return null;
    }
}
