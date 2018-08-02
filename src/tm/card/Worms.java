package tm.card;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
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
            public void render(Graphics g, int x, int y, Game game) {
                g.setColor(Color.LIGHT_GRAY);
                Resources.PLANT.render(g, x, y, true);
                g.drawString("/2", x + 24, y + 12);
                Tags.MICROBE.render(g, x + 45, y);
            }
        };
    }
    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Oxygen must be at least 4%");
    }
}
