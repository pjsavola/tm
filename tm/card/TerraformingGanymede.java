package tm.card;

import java.awt.Graphics;
import java.awt.Point;

import tm.Card;
import tm.Game;
import tm.Renderer;
import tm.Tags;
import tm.action.Action;
import tm.action.AddTerraformingRatingAction;

public class TerraformingGanymede extends Card {

    public TerraformingGanymede() {
        super("Terraforming Ganymede", 33, Tags.SPACE_JOVIAN);
    }

    @Override
    public int getVPs() {
        return 2;
    }

    @Override
    public Action getInitialAction(Game game) {
        final int jovianCount = game.getCurrentPlayer().getTags().getCount(Tags.Type.JOVIAN);
        return new AddTerraformingRatingAction(jovianCount + 1) {
            @Override
            public Point render(Graphics g, int x, int y, Game game) {
                final Point p1 = Renderer.renderImage(g, "images/big_icon_tr.png", x, y);
                final Point p2 = Renderer.renderText(g, " x ", p1.x + 2, y + 8, false);
                final Point p3 = Tags.JOVIAN.render(g, p2.x + 2, y + 6, true);
                return new Point(p3.x, p1.y);
            }
        };
    }
}
