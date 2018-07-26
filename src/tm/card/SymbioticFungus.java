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
import tm.Tags;
import tm.action.Action;
import tm.action.AddMarkerAction;
import tm.action.CardAction;

public class SymbioticFungus extends Card {

    private final Action action = new CardAction(true, ActionType.SYMBIOTIC_FUNGUS) {
        @Override
        protected Action getAction(Game game) {
            return new AddMarkerAction(game.getCurrentPlayer().getPlayedCards()) {
                @Override
                protected String getTitle() {
                    return "Add microbe to card...";
                }

                @Override
                protected Stream<CardWithMarkers> filter(Stream<CardWithMarkers> stream) {
                    return stream.filter(card -> card.getTags().has(Tags.Type.MICROBE));
                }
            };
        }
    };

    public SymbioticFungus() {
        super("Symbiotic Fungus", 4, Tags.MICROBE);
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getTemperature() >= -14 - tolerance * 2;
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("It must be -14C or warmer");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "Add 1 microbe to another card");
    }
}
