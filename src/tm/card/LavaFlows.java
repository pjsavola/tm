package tm.card;

import java.awt.Graphics;
import java.awt.Point;

import tm.Card;
import tm.Game;
import tm.Renderer;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddTemperatureAction;
import tm.action.PlaceTileAction;

public class LavaFlows extends Card {

    public LavaFlows() {
        super("Lava Flows", 18, Tags.EVENT);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new AddTemperatureAction(),
            new AddTemperatureAction(),
            new PlaceTileAction(Tile.Type.LAVA_FLOWS) {
                @Override
                public Point render(Graphics g, int x, int y, Game game) {
                    final Point p = super.render(g, x, y, game);
                    return Renderer.renderText(g, "on Volcano", p.x + 4, y + 4, false);
                }
            }
        );
    }
}
