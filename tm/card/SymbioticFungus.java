package tm.card;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import tm.ActionType;
import tm.Card;
import tm.CardWithMarkers;
import tm.Game;
import tm.Renderer;
import tm.Tags;
import tm.action.Action;
import tm.action.AddMarkerAction;
import tm.action.CardAction;
import tm.requirement.TemperatureRequirement;

public class SymbioticFungus extends Card {

    private final CardAction action = new CardAction(true, ActionType.SYMBIOTIC_FUNGUS) {
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

                @Override
                public Point render(Graphics g, int x, int y, Game game) {
                    final Point p = Tags.MICROBE.render(g, x, y, true);
                    return Renderer.renderMarker(g, p.x + 4, y);
                }
            };
        }
    };

    public SymbioticFungus() {
        super("Symbiotic Fungus", 4, Tags.MICROBE, new TemperatureRequirement(-14, true));
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }
}
