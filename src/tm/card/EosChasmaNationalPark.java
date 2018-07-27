package tm.card;

import java.util.Arrays;
import java.util.Collections;
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
import tm.action.IncomeDeltaAction;
import tm.action.ResourceDeltaAction;
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
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new IncomeDeltaAction(new Resources(2)),
            new ResourceDeltaAction(Resources.PLANT_3),
            new AddMarkerAction(game.getCurrentPlayer().getPlayedCards()) {
                @Override
                protected Stream<CardWithMarkers> filter(Stream<CardWithMarkers> stream) {
                    return stream.filter(card -> card.getTags().has(Tags.Type.ANIMAL));
                }
            }
        );
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("It must be -12C or warmer");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("2 money income", "3 plants", "1 animal marker");
    }
}
