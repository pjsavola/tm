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
        };
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 animal marker");
    }
}
