package tm.card;

import java.awt.Graphics;
import java.awt.Point;

import tm.Card;
import tm.Game;
import tm.Renderer;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.PlaceTileAction;
import tm.effect.DiscountEffect;

public class ResearchOutpost extends Card implements DiscountEffect {

    public ResearchOutpost() {
        super("Research Outpost", 18, Tags.SCIENCE.combine(Tags.BUILDING_CITY), null, true);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.CITY, true) {
            @Override
            public Point render(Graphics g, int x, int y, Game game) {
                final Point p = super.render(g, x, y, game);
                return Renderer.renderText(g, "isolated", p.x + 4, y + 4, false);
            }
        };
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        DiscountEffect.render(g, x, y, "images/icon_card.png", "-1");
    }

    @Override
    public int getDiscount(Card card) {
        return 1;
    }
}
