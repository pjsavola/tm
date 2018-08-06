package tm.card;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.annotation.Nullable;
import tm.Card;
import tm.CardWithMarkers;
import tm.Game;
import tm.ImageCache;
import tm.Renderer;
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
        return new PlaceTileAction(Tile.Type.ECOLOGICAL_ZONE) {
            @Override
            public Point render(Graphics g, int x, int y, Game game) {
                final Point p = super.render(g, x, y, game);
                g.setColor(Color.WHITE);
                return Renderer.renderText(g, "adjacent to Greenery", p.x + 4, y + 4, false);
            }
        };
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
