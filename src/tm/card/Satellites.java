package tm.card;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import tm.Card;
import tm.Game;
import tm.Renderer;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class Satellites extends Card {

    public Satellites() {
        super("Satellites", 10, Tags.SPACE);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(null) {
            @Override
            public Resources getDelta(Game game) {
                return new Resources(game.getCurrentPlayer().getTags().getCount(Tags.Type.SPACE) + 1);
            }
            @Override
            public Point render(Graphics g, int x, int y, Game game) {
                Point p;
                p = Resources.EMPTY.renderMoney(g, x, y, true, false);
                g.setColor(Color.LIGHT_GRAY);
                p = Renderer.renderText(g, " x ", p.x + 2, y + 4, false);
                p = Tags.SPACE.render(g, p.x + 2, y, true);
                return new Point(p.x, y + 18);
            }
        };
    }
}
