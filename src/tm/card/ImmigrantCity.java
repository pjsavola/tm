package tm.card;

import java.awt.Graphics;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.IncomeDeltaAction;
import tm.action.PlaceTileAction;
import tm.effect.PlaceTileEffect;
import tm.effect.PlayCardEffect;

public class ImmigrantCity extends Card implements PlaceTileEffect, PlayCardEffect {

    public ImmigrantCity() {
        super("Immigrant City", 13, Tags.BUILDING_CITY, null, true);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(-2, 0, 0, 0, -1, 0);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.CITY);
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        g.drawImage(Tile.Type.CITY.getIcon(), x, y, null);
        Resources.MONEY.render(g, x + 30, y + 4, true);
    }

    @Nullable
    @Override
    public Action tilePlaced(Tile tile) {
        if (Tile.isCity(tile.getType())) {
            return new IncomeDeltaAction(Resources.MONEY);
        }
        return null;
    }

    @Nullable
    @Override
    public Action cardPlayed(Card card) {
        if (card instanceof PhobosSpaceHaven) {
            return new IncomeDeltaAction(Resources.MONEY);
        } else if (card instanceof GanymedeColony) {
            return new IncomeDeltaAction(Resources.MONEY);
        }
        return null;
    }
}
