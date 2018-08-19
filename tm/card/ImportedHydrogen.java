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
import tm.action.ResourceDeltaAction;

public class ImportedHydrogen extends Card {

    public ImportedHydrogen() {
        super("Imported Hydrogen", 16, Tags.SPACE_EARTH_EVENT);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new AddWaterAction(),
            new AddMarkerAction(game.getCurrentPlayer().getPlayedCards()) {
                @Override
                protected String getTitle() {
                    return "Place 3 microbes, 2 animals or select nothing to get 3 plants";
                }

                @Override
                protected Stream<CardWithMarkers> filter(Stream<CardWithMarkers> stream) {
                    return stream.filter(card -> card.getTags().has(Tags.Type.MICROBE) || card.getTags().has(Tags.Type.ANIMAL));
                }

                @Override
                protected Action onEmptySelection() {
                    return new ResourceDeltaAction(Resources.PLANT_3);
                }

                @Override
                protected int getMarkerCount(CardWithMarkers card) {
                    return card.getTags().has(Tags.Type.MICROBE) ? 3 : 2;
                }

                @Override
                public Point render(Graphics g, int x, int y, Game game) {
                    final Point p1 = Resources.PLANT_3.render(g, x, y, false);
                    g.setColor(Color.WHITE);
                    Renderer.renderText(g, "OR", p1.x + 10, y + 4, false);
                    final Point p = Tags.ANIMAL.render(g, x, p1.y + 2, true);
                    final Point p2 = Renderer.renderMarker(g, p.x + 4, p1.y + 2);
                    final Point p6 = Renderer.renderMarker(g, p2.x + 4, p1.y + 2);
                    g.setColor(Color.WHITE);
                    Renderer.renderText(g, "OR", p6.x + 4, p1.y + 4, false);
                    final Point p3 = Tags.MICROBE.render(g, x, p.y + 2, true);
                    final Point p4 = Renderer.renderMarker(g, p3.x + 4, p.y + 2);
                    final Point p5 = Renderer.renderMarker(g, p4.x + 4, p.y + 2);
                    return Renderer.renderMarker(g, p5.x + 4, p.y + 2);
                }
            }
        );
    }
}
