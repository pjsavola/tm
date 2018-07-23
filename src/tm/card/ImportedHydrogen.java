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

public class ImportedHydrogen extends Card {

    public ImportedHydrogen() {
        super("Imported Hydrogen", 16, Tags.SPACE.combine(Tags.EARTH).combine(Tags.EVENT));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new AddMarkerAction() {
                @Override
                protected String getTitle() {
                    return "Select microbe or animal card, or nothing to get 3 plants";
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
            },
            new AddWaterAction()
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("3 plants /", "3 microbes /", "2 animals", "1 water");
    }
}
