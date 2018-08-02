package tm.action;

import java.awt.Graphics;
import java.awt.Point;

import tm.CardWithMarkers;
import tm.Game;
import tm.Renderer;
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
    public Point render(Graphics g, int x, int y, Game game) {
        final boolean negative = delta < 0;
        for (int i = 0; i < Math.abs(delta); i++) {
            Renderer.renderMarker(g, x + i * 14, y);
            if (negative) {
                Renderer.renderX(g, x + i * 14, y, 12, 12);
            }
        }
        return new Point(x + Math.abs(delta) * 14 - 2, y + 12);
    }
}
