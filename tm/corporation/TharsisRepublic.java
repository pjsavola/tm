package tm.corporation;

import java.awt.Graphics;
import java.awt.Image;

import javax.annotation.Nullable;
import tm.Card;
import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.action.PlaceTileAction;
import tm.action.ResourceDeltaAction;
import tm.card.GanymedeColony;
import tm.card.PhobosSpaceHaven;
import tm.effect.PlaceTileEffect;
import tm.effect.PlayCardEffect;

public class TharsisRepublic extends Corporation implements PlaceTileEffect, PlayCardEffect {

    public TharsisRepublic() {
        super("Tharsis Republic", Tags.BUILDING, true);
    }

    @Override
    public Resources getResourceDelta() {
        return new Resources(40);
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(2); // Initial cities in solo game
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.CITY);
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        final Image image = Tile.Type.CITY.getIcon();
        g.drawImage(image, x, y + 6, null);
        final int currentX = image.getWidth(null);
        Resources.MONEY_3.render(g, currentX + 4, y, false);
        Resources.MONEY.render(g, currentX + 4, y + 20, true);
    }

    @Nullable
    @Override
    public Action tilePlaced(Tile tile) {
        if (Tile.isCity(tile.getType())) {
            return new ActionChain(
                new ResourceDeltaAction(Resources.MONEY_3),
                new IncomeDeltaAction(Resources.MONEY)
            );
        }
        return null;
    }

    @Nullable
    @Override
    public Action cardPlayed(Card card) {
        if (card instanceof PhobosSpaceHaven) {
            return new ResourceDeltaAction(new Resources(3));
        } else if (card instanceof GanymedeColony) {
            return new ResourceDeltaAction(new Resources(3));
        }
        return null;
    }
}
