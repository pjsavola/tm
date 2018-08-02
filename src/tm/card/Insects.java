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

public class Insects extends Card {

    public Insects() {
        super("Insects", 9, Tags.MICROBE, new OxygenRequirement(6, true));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(null) {
            @Override
            public Resources getDelta(Game game) {
                return new Resources(0, 0, 0, game.getCurrentPlayer().getTags().getCount(Tags.Type.PLANT), 0, 0);
            }
            @Override
            public void render(Graphics g, int x, int y, Game game) {
                g.setColor(Color.LIGHT_GRAY);
                Resources.PLANT.render(g, x, y, true);
                g.drawString("/", x + 24, y + 12);
                Tags.PLANT.render(g, x + 45, y);
            }
        };
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 6% oxygen");
    }
}
