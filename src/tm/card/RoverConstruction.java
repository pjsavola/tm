package tm.card;

import java.awt.Graphics;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.ImageCache;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.ResourceDeltaAction;
import tm.effect.PlaceTileEffect;
import tm.effect.PlayCardEffect;

public class RoverConstruction extends Card implements PlaceTileEffect, PlayCardEffect {

    public RoverConstruction() {
        super("Rover Construction", 8, Tags.BUILDING, null, true);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        g.drawImage(ImageCache.getImage("images/icon_city.png"), x, y, null);
        new Resources(2).render(g, x + 30, y + 4, false);
    }

    @Nullable
    @Override
    public Action tilePlaced(Tile tile) {
        if (Tile.isCity(tile.getType())) {
            return new ResourceDeltaAction(new Resources(2));
        }
        return null;
    }

    @Nullable
    @Override
    public Action cardPlayed(Card card) {
        if (card instanceof PhobosSpaceHaven) {
            return new ResourceDeltaAction(new Resources(2));
        } else if (card instanceof GanymedeColony) {
            return new ResourceDeltaAction(new Resources(3));
        }
        return null;
    }
}
