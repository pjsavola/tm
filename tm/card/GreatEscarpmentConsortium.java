package tm.card;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import tm.Card;
import tm.Renderer;
import tm.Resources;
import tm.Tags;
import tm.requirement.ProductionRequirement;

// Decrease any steel income by 1 is done from dummy player
public class GreatEscarpmentConsortium extends Card {

    public GreatEscarpmentConsortium() {
        super("Great Escarpment Cons.", 6, Tags.EMPTY, new ProductionRequirement(Resources.STEEL) {
            @Override
            public void render(Graphics g, int x, int y) {
                final Point p = Resources.EMPTY.renderSteel(g, x, y, true, false);
                g.setColor(Color.RED);
                Renderer.renderText(g, "≥ 1", p.x + 3, y + 4, false);
            }
        });
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.STEEL;
    }
}
