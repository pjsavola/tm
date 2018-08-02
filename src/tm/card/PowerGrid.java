package tm.card;

import java.awt.Color;
import java.awt.Graphics;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class PowerGrid extends Card {

    public PowerGrid() {
        super("Power Grid", 18, Tags.POWER);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(null) {
            @Override
            public Resources getDelta(Game game) {
                return new Resources(0, 0, 0, 0, game.getCurrentPlayer().getTags().getCount(Tags.Type.POWER) + 1, 0);
            }
            @Override
            public void render(Graphics g, int x, int y, Game game) {
                g.setColor(Color.LIGHT_GRAY);
                Resources.ENERGY.render(g, x, y, true);
                g.drawString("/", x + 24, y + 12);
                Tags.POWER.render(g, x + 45, y);
            }
        };
    }
}
