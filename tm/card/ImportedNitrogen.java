package tm.card;

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
import tm.action.AddTerraformingRatingAction;

public class ImportedNitrogen extends Card {

    public ImportedNitrogen() {
        super("Imported Nitrogen", 23, Tags.SPACE_EARTH_EVENT);
    }

    @Override
    public Resources getResourceDelta() {
        return Resources.PLANT_4;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new AddTerraformingRatingAction(),
            new AddMarkerAction(game.getCurrentPlayer().getPlayedCards()) {
                @Override
                protected String getTitle() {
                    return "Select microbe card to gain 3 markers";
                }

                @Override
                protected Stream<CardWithMarkers> filter(Stream<CardWithMarkers> stream) {
                    return stream.filter(card -> card.getTags().has(Tags.Type.MICROBE));
                }

                @Override
                protected int getMarkerCount(CardWithMarkers card) {
                    return 3;
                }

                @Override
                public Point render(Graphics g, int x, int y, Game game) {
                    final Point p = Tags.MICROBE.render(g, x, y, true);
                    final Point p2 = Renderer.renderMarker(g, p.x + 4, y);
                    final Point p3 = Renderer.renderMarker(g, p2.x + 4, y);
                    return Renderer.renderMarker(g, p3.x + 4, y);
                }
            },
            new AddMarkerAction(game.getCurrentPlayer().getPlayedCards()) {
                @Override
                protected String getTitle() {
                    return "Select animal card to gain 2 markers";
                }

                @Override
                protected Stream<CardWithMarkers> filter(Stream<CardWithMarkers> stream) {
                    return stream.filter(card -> card.getTags().has(Tags.Type.ANIMAL));
                }

                @Override
                protected int getMarkerCount(CardWithMarkers card) {
                    return 2;
                }

                @Override
                public Point render(Graphics g, int x, int y, Game game) {
                    final Point p = Tags.ANIMAL.render(g, x, y, true);
                    final Point p2 = Renderer.renderMarker(g, p.x + 4, y);
                    return Renderer.renderMarker(g, p2.x + 4, y);
                }
            }
        );
    }
}
