package tm.card;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import tm.Card;
import tm.CardWithMarkers;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.AddMarkerAction;

public class AerobrakedAmmoniaAsteroid extends Card {

    public AerobrakedAmmoniaAsteroid() {
        super("Aerobraked Ammonia Asteroid", 26, Tags.SPACE_EVENT);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(0, 0, 0, 1, 0, 3);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new AddMarkerAction(game.getCurrentPlayer().getPlayedCards()) {
            @Override
            protected String getTitle() {
                return "Select microbe card to gain 2 markers";
            }

            @Override
            protected Stream<CardWithMarkers> filter(Stream<CardWithMarkers> stream) {
                return stream.filter(card -> card.getTags().has(Tags.Type.MICROBE));
            }

            @Override
            protected int getMarkerCount(CardWithMarkers card) {
                return 2;
            }
        };
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("2 microbes");
    }
}
