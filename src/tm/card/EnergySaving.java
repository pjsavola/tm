package tm.card;

import java.awt.Color;
import java.awt.Graphics;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class EnergySaving extends Card {

    public EnergySaving() {
        super("Energy Saving", 15, Tags.POWER);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(null) {
            @Override
            public Resources getDelta(Game game) {
                return new Resources(0, 0, 0, 0, game.getCityCount(false), 0);
            }
            @Override
            public void render(Graphics g, int x, int y, Game game) {
                g.setColor(Color.LIGHT_GRAY);
                Resources.ENERGY.render(g, x, y, true);
                g.drawString("/", x + 24, y + 12);
                g.drawImage(Tile.Type.CITY.getIcon(), x + 28, y, null);
            }
        };
    }
}
