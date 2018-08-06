package tm.card;

import java.awt.Color;
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
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.AddMarkerAction;
import tm.action.CardAction;
import tm.action.ResourceDeltaAction;
import tm.requirement.TemperatureRequirement;

public class ExtremeColdFungus extends Card {

    private final CardAction action = new CardAction(true, ActionType.EXTREME_COLD_FUNGUS) {
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
                protected int getMarkerCount(CardWithMarkers card) {
                    return 2;
                }

                @Override
                protected Action onEmptySelection() {
                    return new ResourceDeltaAction(Resources.PLANT);
                }

                @Override
                public Point render(Graphics g, int x, int y, Game game) {
                    final Point p1 = Resources.PLANT.render(g, x, y, false);
                    g.setColor(Color.WHITE);
                    Renderer.renderText(g, "OR", p1.x + 10, y + 4, false);
                    final Point p = Tags.MICROBE.render(g, x, p1.y + 4, true);
                    final Point p2 = Renderer.renderMarker(g, p.x + 4, p1.y + 4);
                    return Renderer.renderMarker(g, p2.x + 4, p1.y + 4);
                }
            };
        }
    };

    public ExtremeColdFungus() {
        super("Extreme-Cold Fungus", 13, Tags.MICROBE, new TemperatureRequirement(-10, false));
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }
}
