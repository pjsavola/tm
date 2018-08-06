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

public class SpecialDesign extends Card {

    public SpecialDesign() {
        super("Special Design", 4, Tags.SCIENCE.combine(Tags.EVENT));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlayCardAction(game.getCurrentPlayer().getCards(), "Play card with Special Design", 0, 2) {
            @Override
            public Point render(Graphics g, int x, int y, Game game) {
                g.setColor(Color.WHITE);
                return Renderer.renderText(g, "Next card has +/- 2 requirements", x, y, false);
            }
        };
    }
}
