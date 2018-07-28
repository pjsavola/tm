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
import tm.action.AddTerraformingRatingAction;
import tm.action.ResourceDeltaAction;

public class ImportedNitrogen extends Card {

    public ImportedNitrogen() {
        super("Imported Nitrogen", 23, Tags.SPACE_EARTH_EVENT);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new ResourceDeltaAction(new Resources(0, 0, 0, 4, 0, 0)),
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
            }
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("4 plants", "1 TR", "3 microbes", "2 animals");
    }
}
