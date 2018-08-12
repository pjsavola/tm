package tm.action;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import tm.Game;
import tm.ImageCache;
import tm.completable.Completable;
import tm.completable.InstantCompletable;
import tm.completable.NopCompletable;

public class AddOxygenAction implements Action {

    @Override
    public boolean check(Game game) {
        return game.getPlanet().getOxygen() < 14;
    }

    @Override
    public Completable begin(Game game) {
        if (!check(game)) {
            return new NopCompletable(game);
        }
        return new InstantCompletable(game) {
            @Override
            public void complete() {
                game.getPlanet().adjustOxygen(1);
                game.getCurrentPlayer().adjustRating(1);
                if (game.getPlanet().getOxygen() == 8) {
                    final Action bonusAction = new AddTemperatureAction();
                    if (bonusAction.check(game)) {
                        game.getActionHandler().addPendingAction(bonusAction);
                    }
                }
            }

            @Override
            public void undo() {
                game.getPlanet().adjustOxygen(-1);
                game.getCurrentPlayer().adjustRating(-1);
            }

            @Override
            public void redo() {
                game.getPlanet().adjustOxygen(1);
                game.getCurrentPlayer().adjustRating(1);
            }
        };
    }

    @Override
    public boolean isOptional() {
        return true;
    }

    @Override
    public Point render(Graphics g, int x, int y, Game game) {
        final Image image = ImageCache.getImage("images/icon_oxygen.png");
        g.drawImage(image, x, y, null);
        return new Point(x + image.getWidth(null), y + image.getHeight(null));
    }
}
