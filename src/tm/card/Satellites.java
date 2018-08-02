package tm.card;

import java.awt.Color;
import java.awt.Graphics;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class Satellites extends Card {

    public Satellites() {
        super("Satellites", 10, Tags.SPACE);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(null) {
            @Override
            public Resources getDelta(Game game) {
                return new Resources(game.getCurrentPlayer().getTags().getCount(Tags.Type.SPACE) + 1);
            }
            @Override
            public void render(Graphics g, int x, int y, Game game) {
                g.setColor(Color.LIGHT_GRAY);
                Resources.MONEY.render(g, x, y, true);
                g.drawString("/", x + 24, y + 12);
                Tags.SPACE.render(g, x + 45, y);
            }
        };
    }
}
