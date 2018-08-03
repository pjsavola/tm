package tm.card;

import java.awt.Graphics;
import java.awt.Point;

import tm.Card;
import tm.Game;
import tm.Renderer;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.AddWaterAction;
import tm.requirement.TemperatureRequirement;

public class ArtificialLake extends Card {

    public ArtificialLake() {
        super("Artificial Lake", 15, Tags.BUILDING, new TemperatureRequirement(-6, true));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new AddWaterAction(Tile.Type.ARTIFICIAL_LAKE) {
            @Override
            public Point render(Graphics g, int x, int y, Game game) {
                final Point p = super.render(g, x, y, game);
                return Renderer.renderText(g, "on land", p.x + 4, y + 4, false);
            }
        };
    }
}
