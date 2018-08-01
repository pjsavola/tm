package tm.card;

import java.awt.Graphics;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.CardWithMarkers;
import tm.Game;
import tm.ImageCache;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.MarkerDeltaAction;
import tm.effect.PlaceTileEffect;
import tm.effect.PlayCardEffect;

public class Pets extends CardWithMarkers implements PlaceTileEffect, PlayCardEffect {

    public Pets() {
        super("Pets", 10, Tags.ANIMAL.combine(Tags.EARTH), null, true, 1, 2);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new MarkerDeltaAction(1, this);
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        g.drawImage(ImageCache.getImage("images/icon_city.png"), x, y, null);
        g.drawString("1 marker", x + 30, y + 16);
    }

    @Nullable
    @Override
    public Action tilePlaced(Tile tile) {
        if (Tile.isCity(tile.getType())) {
            return new MarkerDeltaAction(1, this);
        }
        return null;
    }

    @Nullable
    @Override
    public Action cardPlayed(Card card) {
        if (card instanceof PhobosSpaceHaven) {
            return new MarkerDeltaAction(1, this);
        } else if (card instanceof GanymedeColony) {
            return new MarkerDeltaAction(1, this);
        }
        return null;
    }
}
