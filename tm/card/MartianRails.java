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
import tm.action.CardAction;
import tm.action.CardActionWithCost;
import tm.action.ResourceDeltaAction;

public class MartianRails extends Card {

    private final CardAction action = new CardActionWithCost(true, "1 money per city on Mars", Resources.ENERGY.negate()) {
        @Override
        protected Action getAction(Game game) {
            return new ResourceDeltaAction(null) {
                @Override
                public Resources getDelta(Game game) {
                    return new Resources(game.getCityCount(true));
                }
                @Override
                public Point render(Graphics g, int x, int y, Game game) {
                    Point p;
                    p = Resources.EMPTY.renderMoney(g, x, y, false, false);
                    g.setColor(Color.LIGHT_GRAY);
                    p = Renderer.renderText(g, " x ", p.x + 2, y + 4, false);
                    g.setColor(Color.RED);
                    g.drawOval(p.x + 1, y - 1, 18, 18);
                    p = Renderer.renderSmallIcon(g, Tile.Type.CITY, p.x + 2, y);
                    return new Point(p.x + 1, p.y + 1);
                }
            };
        }
    };

    public MartianRails() {
        super("Martian Rails", 13, Tags.BUILDING);
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }
}
