package tm.card;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.ActionType;
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

public class MartianRail extends Card {

    private final CardAction action = new CardActionWithCost(true, ActionType.MARTIAN_RAIL, Resources.ENERGY.negate()) {
        @Override
        protected Action getAction(Game game) {
            return new ResourceDeltaAction(null) {
                @Override
                public Resources getDelta(Game game) {
                    return new Resources(game.getCityCount(true));
                }
                @Override
                public Point render(Graphics g, int x, int y, Game game) {
                    g.setColor(Color.LIGHT_GRAY);
                    Point p;
                    p = Resources.EMPTY.renderMoney(g, x, y, false, false);
                    p = Renderer.renderText(g, "/", p.x + 2, y + 4, false);
                    Renderer.renderVPCircle(g, p.x + 2, y);
                    p = Renderer.renderIcon(g, Tile.Type.CITY, p.x + 2, y);
                    return p;
                }
            };
        }
    };

    public MartianRail() {
        super("Martian Rail", 13, Tags.BUILDING);
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "1 money for each city", "-1 energy");
    }
}
