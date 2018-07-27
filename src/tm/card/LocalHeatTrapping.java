package tm.card;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import tm.Card;
import tm.CardWithMarkers;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddMarkerAction;
import tm.action.ResourceDeltaAction;

public class LocalHeatTrapping extends Card {

    public LocalHeatTrapping() {
        super("Local Heat Trapping", 1, Tags.EVENT);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new AddMarkerAction(game.getCurrentPlayer().getPlayedCards()) {
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
                    return new ResourceDeltaAction(new Resources(0, 0, 0, 4, 0, 0));
                }

                @Override
                protected int getMarkerCount(CardWithMarkers card) {
                    return 2;
                }
            },
            new ResourceDeltaAction(new Resources(0, 0, 0, 0, 0, -5))
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("4 plants OR 2 animals", "-5 heat");
    }
}
