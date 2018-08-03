package tm.card;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import tm.Card;
import tm.Game;
import tm.Renderer;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.ResourceDeltaAction;

public class Greenhouses extends Card {

    public Greenhouses() {
        super("Greenhouses", 6, Tags.PLANT.combine(Tags.BUILDING));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ResourceDeltaAction(null) {
            @Override
            public Resources getDelta(Game game) {
                return new Resources(0, 0, 0, game.getCityCount(false), 0, 0);
            }

            @Override
            public Point render(Graphics g, int x, int y, Game game) {
                g.setColor(Color.LIGHT_GRAY);
                Point p;
                p = Resources.EMPTY.renderPlants(g, x, y, false, false);
                p = Renderer.renderText(g, "/", p.x + 2, y + 4, false);
                p = Renderer.renderIcon(g, Tile.Type.CITY, p.x + 2, y);
                return p;
            }
        };
    }
}
