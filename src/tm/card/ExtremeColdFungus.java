package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import tm.ActionType;
import tm.Card;
import tm.CardWithMarkers;
import tm.Game;
import tm.Planet;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.AddMarkerAction;
import tm.action.CardAction;
import tm.action.ResourceDeltaAction;

public class ExtremeColdFungus extends Card {

    private final Action action = new CardAction(true, ActionType.EXTREME_COLD_FUNGUS) {
        @Override
        protected Action getAction(Game game) {
            return new AddMarkerAction(game.getCurrentPlayer().getPlayedCards()) {
                @Override
                protected String getTitle() {
                    return "Gain 1 plant or add 2 microbes to card...";
                }

                @Override
                protected Stream<CardWithMarkers> filter(Stream<CardWithMarkers> stream) {
                    return stream.filter(card -> card.getTags().has(Tags.Type.MICROBE));
                }

                @Override
                protected Action onEmptySelection() {
                    return new ResourceDeltaAction(Resources.PLANT);
                }
            };
        }
    };

    public ExtremeColdFungus() {
        super("Extreme-Cold Fungus", 13, Tags.MICROBE);
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getTemperature() <= -10 + tolerance * 2;
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("It must be -10C or colder");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "Add 2 microbes to another card /", "Gain 1 plant");
    }
}
