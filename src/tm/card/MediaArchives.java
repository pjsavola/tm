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
import tm.action.ResourceDeltaAction;

public class MediaArchives extends Card {

    public MediaArchives() {
        super("Media Archives", 8, Tags.EARTH);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ResourceDeltaAction(null) {
            @Override
            public Resources getDelta(Game game) {
                return new Resources(game.getCurrentPlayer().getTags().getCount(Tags.Type.EVENT));
            }
            @Override
            public Point render(Graphics g, int x, int y, Game game) {
                g.setColor(Color.LIGHT_GRAY);
                Point p;
                p = Resources.EMPTY.renderMoney(g, x, y, false, false);
                p = Renderer.renderText(g, "/", p.x + 2, y, false);
                p = Tags.EVENT.render(g, p.x + 2, y, true);
                return p;
            }
        };
    }
}
