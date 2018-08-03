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
import tm.action.AddOxygenAction;
import tm.action.PlaceTileAction;
import tm.requirement.TemperatureRequirement;

public class Mangrove extends Card {

    public Mangrove() {
        super("Mangrove", 12, Tags.PLANT, new TemperatureRequirement(4, true));
    }

    @Override
    public int getVPs() {
        return 1;
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
