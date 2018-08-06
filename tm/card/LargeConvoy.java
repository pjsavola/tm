package tm.card;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.stream.Stream;

import tm.Card;
import tm.CardWithMarkers;
import tm.Game;
import tm.Renderer;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddMarkerAction;
import tm.action.AddWaterAction;
import tm.action.DrawCardsAction;
import tm.action.ResourceDeltaAction;

public class LargeConvoy extends Card {

    public LargeConvoy() {
        super("Large Convoy", 36, Tags.SPACE_EARTH_EVENT);
    }

    @Override
    public int getVPs() {
        return 2;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new AddMarkerAction(game.getCurrentPlayer().getPlayedCards()) {
                @Override
                protected String getTitle() {
                    return "Get 5 plants or add 4 animasl to card...";
                }

                @Override
                protected Stream<CardWithMarkers> filter(Stream<CardWithMarkers> stream) {
                    return stream.filter(card -> card.getTags().has(Tags.Type.ANIMAL));
                }

                @Override
                protected Action onEmptySelection() {
                    return new ResourceDeltaAction(Resources.PLANT_5);
                }

                @Override
                protected int getMarkerCount(CardWithMarkers card) {
                    return 4;
                }

                @Override
                public Point render(Graphics g, int x, int y, Game game) {
                    final Point p1 = Resources.PLANT_5.render(g, x, y, false);
                    g.setColor(Color.WHITE);
                    Renderer.renderText(g, "OR", p1.x + 10, y + 4, false);
                    final Point p = Tags.ANIMAL.render(g, x, p1.y + 4, true);
                    final Point p2 = Renderer.renderMarker(g, p.x + 4, p1.y + 4);
                    final Point p3 = Renderer.renderMarker(g, p2.x + 4, p1.y + 4);
                    final Point p4 = Renderer.renderMarker(g, p3.x + 4, p1.y + 4);
                    return Renderer.renderMarker(g, p4.x + 4, p1.y + 4);
                }
            },
            new AddWaterAction(),
            new DrawCardsAction(2, false, false)
        );
    }
}
