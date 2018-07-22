package tm.card;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import tm.Card;
import tm.CardWithMarkers;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddMarkerAction;
import tm.action.AddWaterAction;
import tm.action.ResourceDeltaAction;

public class ImportedHydrogen extends Card {

    public ImportedHydrogen() {
        super("Imported Hydrogen", 16, new Tags().space().earth().event());
    }

    @Override
    public Action getInitialAction() {
        return new ActionChain(
            new AddMarkerAction() {
                @Override
                protected String getTitle() {
                    return "Select microbe or animal card, or nothing to get 3 plants";
                }

                @Override
                protected Stream<CardWithMarkers> filter(Stream<CardWithMarkers> stream) {
                    return stream.filter(card -> card.getTags().hasMicrobe() || card.getTags().hasAnimal());
                }

                @Override
                protected Action onEmptySelection() {
                    return new ResourceDeltaAction(new Resources(0, 0, 0, 3, 0, 0));
                }

                @Override
                protected int getMarkerCount(CardWithMarkers card) {
                    return card.getTags().hasMicrobe() ? 3 : 2;
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
