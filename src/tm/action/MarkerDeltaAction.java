package tm.action;

import java.awt.Color;
import java.awt.Graphics;

import tm.CardWithMarkers;
import tm.Game;
import tm.completable.Completable;
import tm.completable.InstantCompletable;

public class MarkerDeltaAction implements Action {

    final int delta;
    final CardWithMarkers card;

    public MarkerDeltaAction(int delta, CardWithMarkers card) {
        this.delta = delta;
        this.card = card;
    }

    @Override
    public boolean check(Game game) {
        return card.getMarkerCount() + delta >= 0;
    }

    @Override
    public Completable begin(Game game) {
        return new InstantCompletable(game) {
            @Override
            public void complete() {
                card.adjustMarkers(delta);
            }

            @Override
            public void undo() {
                card.adjustMarkers(-delta);
            }

            @Override
            public void redo() {
                card.adjustMarkers(delta);
            }
        };
    }

    @Override
    public void render(Graphics g, int x, int y) {
        render(g, x, y, delta);
    }

    public static void render(Graphics g, int x, int y, int amount) {
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(x, y, 20, 20);
        final String markerString = Integer.toString(amount);
        final int markerWidth = g.getFontMetrics().stringWidth(markerString);
        g.drawString(markerString, x + (20 - markerWidth) / 2, y + 13);
    }
}
