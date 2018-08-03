package tm.card;

import java.awt.Graphics;
import java.awt.Point;

import tm.Card;
import tm.Game;
import tm.Renderer;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.PlaceTileAction;
import tm.requirement.OxygenRequirement;

public class NaturalPreserve extends Card {

    public NaturalPreserve() {
        super("Natural Preserve", 9, Tags.SCIENCE_BUILDING, new OxygenRequirement(4, false));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.MONEY;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.NATURAL_PRESERVE, true) {
            @Override
            public Point render(Graphics g, int x, int y, Game game) {
                final Point p = super.render(g, x, y, game);
                return Renderer.renderText(g, "isolated", p.x + 4, y + 4, false);
            }
        };
    }
}
