package tm.card;

import java.awt.Color;
import java.awt.Graphics;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ResourceDeltaAction;

public class MediaArchives extends Card {

    public MediaArchives() {
        super("Media Archives", 8, Tags.EARTH);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ResourceDeltaAction(null) {
            @Override
            public Resources getDelta(Game game) {
                return new Resources(game.getCurrentPlayer().getTags().getCount(Tags.Type.EVENT));
            }
            @Override
            public void render(Graphics g, int x, int y, Game game) {
                g.setColor(Color.LIGHT_GRAY);
                Resources.MONEY.render(g, x, y, false);
                g.drawString("/", x + 24, y + 12);
                Tags.EVENT.render(g, x + 45, y);
            }
        };
    }
}
