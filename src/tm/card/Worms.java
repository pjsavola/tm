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
import tm.action.Action;
import tm.action.IncomeDeltaAction;
import tm.requirement.OxygenRequirement;

public class Worms extends Card {

    public Worms() {
        super("Worms", 8, Tags.MICROBE, new OxygenRequirement(4, true));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(null) {
            @Override
            public Resources getDelta(Game game) {
                return new Resources(0, 0, 0, (game.getCurrentPlayer().getTags().getCount(Tags.Type.MICROBE) + 1) / 2, 0, 0);
            }
            @Override
            public Point render(Graphics g, int x, int y, Game game) {
                g.setColor(Color.LIGHT_GRAY);
                Point p;
                p = Resources.EMPTY.renderPlants(g, x, y, true, false);
                p = Renderer.renderText(g, "/ 2", p.x + 2, y + 4, false);
                p = Tags.MICROBE.render(g, p.x + 2, y, true);
                return new Point(p.x, y + 18);
            }
        };
    }
    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Oxygen must be at least 4%");
    }
}
