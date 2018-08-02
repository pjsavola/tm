package tm.card;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Collections;
import java.util.List;

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
                g.setColor(Color.LIGHT_GRAY);
                Point p;
                p = Resources.EMPTY.renderMoney(g, x, y, true, false);
                p = Renderer.renderText(g, "/", p.x + 2, y, false);
                Renderer.renderVPCircle(g, p.x + 2, y);
                p = Renderer.renderIcon(g, Tile.Type.CITY, p.x + 2, y);
                return p;            }
        };
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Oxygen must be at least 5%");
    }
}
