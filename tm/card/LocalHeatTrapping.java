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
import tm.action.AddMarkerAction;
import tm.action.ResourceDeltaAction;

public class LocalHeatTrapping extends Card {

    public LocalHeatTrapping() {
        super("Local Heat Trapping", 1, Tags.EVENT);
    }

    @Override
    public Resources getResourceDelta() {
        return new Resources(0, 0, 0, 0, 0, -5);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new AddMarkerAction(game.getCurrentPlayer().getPlayedCards()) {
            @Override
            protected String getTitle() {
                return "Select card for 2 markers, or nothing to get 4 plants";
            }

            @Override
            protected Stream<CardWithMarkers> filter(Stream<CardWithMarkers> stream) {
                return stream.filter(card -> card.getTags().has(Tags.Type.ANIMAL));
            }

            @Override
            protected Action onEmptySelection() {
                return new ResourceDeltaAction(Resources.PLANT_4);
            }

            @Override
            protected int getMarkerCount(CardWithMarkers card) {
                return 2;
            }

            @Override
            public Point render(Graphics g, int x, int y, Game game) {
                final Point p = Resources.PLANT_4.render(g, x, y, false);
                g.setColor(Color.WHITE);
                Renderer.renderText(g, "OR", p.x + 10, y + 4, false);
                final Point p2 = Tags.ANIMAL.render(g, x, p.y + 4, true);
                final Point p3 = Renderer.renderMarker(g, p2.x + 4, p.y + 4);
                return Renderer.renderMarker(g, p3.x + 4, p.y + 4);
            }
        };
    }
}
