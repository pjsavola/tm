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
import tm.action.IncomeDeltaAction;
import tm.requirement.OxygenRequirement;

public class Zeppelins extends Card {

    public Zeppelins() {
        super("Zeppelins", 13, Tags.EMPTY, new OxygenRequirement(5, true));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(null) {
            @Override
            public Resources getDelta(Game game) {
                return new Resources(game.getCityCount(true));
            }
            @Override
            public Point render(Graphics g, int x, int y, Game game) {
                Point p;
                p = Resources.EMPTY.renderMoney(g, x, y, true, false);
                g.setColor(Color.LIGHT_GRAY);
                p = Renderer.renderText(g, " x ", p.x + 2, y + 4, false);
                g.setColor(Color.RED);
                g.drawOval(p.x + 1, y - 1, 18, 18);
                p = Renderer.renderSmallIcon(g, Tile.Type.CITY, p.x + 2, y);
                return new Point(p.x + 1, p.y + 1);            }
        };
    }
}
