package tm.card;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import tm.Card;
import tm.Game;
import tm.Renderer;
import tm.Tags;
import tm.action.Action;
import tm.action.PlayCardAction;

public class IndenturedWorkers extends Card {

    public IndenturedWorkers() {
        super("Indentured Workers", 0, Tags.EVENT);
    }

    @Override
    public int getVPs() {
        return -1;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlayCardAction(game.getCurrentPlayer().getCards(), "Play card with Indentured Workers", 8, 0) {
            @Override
            public Point render(Graphics g, int x, int y, Game game) {
                g.setColor(Color.WHITE);
                return Renderer.renderText(g, "Next card cost -8", x, y, false);
            }
        };
    }
}
