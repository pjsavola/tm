package tm.card;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import tm.Card;
import tm.Renderer;
import tm.Resources;
import tm.Tags;
import tm.requirement.ProductionRequirement;

// Decrease any titanium income by 1 is done from dummy player
public class AsteroidMiningConsortium extends Card {

    public AsteroidMiningConsortium() {
        super("Asteroid Mining Cons.", 13, Tags.JOVIAN, new ProductionRequirement(Resources.TITANIUM) {
            @Override
            public void render(Graphics g, int x, int y) {
                final Point p = Resources.EMPTY.renderTitanium(g, x, y, true, false);
                g.setColor(Color.RED);
                Renderer.renderText(g, "≥ 1", p.x + 3, y + 4, false);
            }
        });
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.TITANIUM;
    }
}
