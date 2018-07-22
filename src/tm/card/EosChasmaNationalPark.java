package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import tm.Card;
import tm.CardWithMarkers;
import tm.Planet;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddMarkerAction;
import tm.action.IncomeDeltaAction;
import tm.action.ResourceDeltaAction;

public class EosChasmaNationalPark extends Card {

    public EosChasmaNationalPark() {
        super("Eos Chasma National Park", 16, new Tags().building().plant());
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getTemperature() <= -12 + tolerance * 2;
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Action getInitialAction() {
        return new ActionChain(
            new IncomeDeltaAction(new Resources(2)),
            new ResourceDeltaAction(new Resources(0, 0, 0, 3, 0, 0)),
            new AddMarkerAction() {
                @Override
                protected Stream<CardWithMarkers> filter(Stream<CardWithMarkers> stream) {
                    return stream.filter(card -> card.getTags().hasAnimal());
                }
            }
        );
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("It must be -12C or colder");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("2 money income", "3 plants", "1 animal marker");
    }
}
