package tm.card;

import java.awt.Graphics;
import java.awt.Point;

import tm.Card;
import tm.Game;
import tm.Renderer;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddOxygenAction;
import tm.action.PlaceTileAction;

public class ProtectedValley extends Card {

    public ProtectedValley() {
        super("Protected Valley", 23, Tags.BUILDING.combine(Tags.PLANT));
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.MONEY_2;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new AddOxygenAction(),
            new PlaceTileAction(Tile.Type.MANGROVE) {
                @Override
                public Point render(Graphics g, int x, int y, Game game) {
                    final Point p = super.render(g, x, y, game);
                    return Renderer.renderText(g, "on Ocean", p.x + 4, y + 4, false);
                }
            }
        );
    }
}
