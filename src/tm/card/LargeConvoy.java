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
import tm.action.AddWaterAction;
import tm.action.ResourceDeltaAction;

public class LargeConvoy extends Card {

    public LargeConvoy() {
        super("Large Convoy", 36, Tags.SPACE.combine(Tags.EARTH).combine(Tags.EVENT));
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
                    return new ResourceDeltaAction(new Resources(0, 0, 0, 5, 0, 0));
                }

                @Override
                protected int getMarkerCount(CardWithMarkers card) {
                    return 4;
                }
            },
            new AddWaterAction()
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 ocean", "2 cards", "5 plants / 4 animals");
    }
}
