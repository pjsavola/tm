package tm.card;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import tm.Card;
import tm.CardWithMarkers;
import tm.Game;
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
        };
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Add 1 marker to any card", "which has any markers");
    }
}
