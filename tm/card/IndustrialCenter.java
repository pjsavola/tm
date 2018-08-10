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
import tm.action.PlaceTileAction;

public class IndustrialCenter extends Card {

    private final CardAction action = new CardActionWithCost(true, "-7 money, 1 steel income", new Resources(-7), Resources.STEEL);
    public IndustrialCenter() {
        super("Industrial Center", 4, Tags.BUILDING);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.INDUSTRIAL_CENTER) {
            @Override
            public Point render(Graphics g, int x, int y, Game game) {
                final Point p = super.render(g, x, y, game);
                g.setColor(Color.WHITE);
                return Renderer.renderText(g, "adjacent to City", p.x + 4, y + 4, false);
            }
        };
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }
}
