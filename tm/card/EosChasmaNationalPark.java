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
import tm.action.AddMarkerAction;
import tm.requirement.TemperatureRequirement;

public class EosChasmaNationalPark extends Card {

    public EosChasmaNationalPark() {
        super("Eos Chasma National Park", 16, Tags.BUILDING.combine(Tags.PLANT), new TemperatureRequirement(-12, true));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Resources getResourceDelta() {
        return Resources.PLANT_3;
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(2);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new AddMarkerAction(game.getCurrentPlayer().getPlayedCards()) {
            @Override
            protected Stream<CardWithMarkers> filter(Stream<CardWithMarkers> stream) {
                return stream.filter(card -> card.getTags().has(Tags.Type.ANIMAL));
            }

            @Override
            public Point render(Graphics g, int x, int y, Game game) {
                final Point p = Tags.ANIMAL.render(g, x, y, true);
                return Renderer.renderMarker(g, p.x + 4, y);
            }
        };
    }
}
