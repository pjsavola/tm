package tm.card;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.stream.Stream;

import tm.Card;
import tm.CardWithMarkers;
import tm.Game;
import tm.Renderer;
import tm.Tags;
import tm.action.Action;
import tm.action.AddMarkerAction;

public class CEOsFavouriteProject extends Card {

    public CEOsFavouriteProject() {
        super("CEO's Favourite Project", 1, Tags.EVENT);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new AddMarkerAction(game.getCurrentPlayer().getPlayedCards()) {
            @Override
            protected String getTitle() {
                    return "Add marker to card...";
                }

            @Override
            protected Stream<CardWithMarkers> filter(Stream<CardWithMarkers> stream) {
                return stream.filter(card -> card.getMarkerCount() > 0);
            }

            @Override
            protected int getMarkerCount(CardWithMarkers card) {
                    return 1;
                }

            @Override
            public Point render(Graphics g, int x, int y, Game game) {
                final Point p = Renderer.renderMarker(g, x, y);
                g.setColor(Color.WHITE);
                final Point p2 = Renderer.renderText(g, "≥ 1", p.x + 3, y + 2, false);
                return Renderer.renderMarker(g, p2.x + 10, y);
            }
        };
    }
}
