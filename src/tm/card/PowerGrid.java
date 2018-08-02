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

public class PowerGrid extends Card {

    public PowerGrid() {
        super("Power Grid", 18, Tags.POWER);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(null) {
            @Override
            public Resources getDelta(Game game) {
                return new Resources(0, 0, 0, 0, game.getCurrentPlayer().getTags().getCount(Tags.Type.POWER) + 1, 0);
            }
            @Override
            public Point render(Graphics g, int x, int y, Game game) {
                g.setColor(Color.LIGHT_GRAY);
                Point p;
                p = Resources.EMPTY.renderEnergy(g, x, y, true, false);
                p = Renderer.renderText(g, "/", p.x + 2, y, false);
                p = Tags.POWER.render(g, p.x + 2, y, true);
                return new Point(p.x, y + 18);
            }
        };
    }
}
