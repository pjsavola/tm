package tm.card;

import java.awt.Color;
import java.awt.Graphics;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.ResourceDeltaAction;

public class Greenhouses extends Card {

    public Greenhouses() {
        super("Greenhouses", 6, Tags.PLANT.combine(Tags.BUILDING));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ResourceDeltaAction(null) {
            @Override
            public Resources getDelta(Game game) {
                return new Resources(0, 0, 0, game.getCityCount(false), 0, 0);
            }

            @Override
            public void render(Graphics g, int x, int y, Game game) {
                g.setColor(Color.LIGHT_GRAY);
                Resources.PLANT.render(g, x, y, false);
                g.drawString("/", x + 24, y + 12);
                g.drawImage(Tile.Type.CITY.getIcon(), x + 28, y, null);
            }
        };
    }
}
