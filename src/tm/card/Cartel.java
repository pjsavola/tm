package tm.card;

import java.awt.Color;
import java.awt.Graphics;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class Cartel extends Card {

    public Cartel() {
        super("Cartel", 8, Tags.EARTH);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(null) {
            @Override
            public Resources getDelta(Game game) {
                return new Resources(game.getCurrentPlayer().getTags().getCount(Tags.Type.EARTH) + 1);
            }
            @Override
            public void render(Graphics g, int x, int y, Game game) {
                g.setColor(Color.LIGHT_GRAY);
                Resources.MONEY.render(g, x, y, true);
                g.drawString("/", x + 24, y + 12);
                Tags.EARTH.render(g, x + 45, y);
            }
        };
    }
}
