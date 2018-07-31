package tm.action;

import java.awt.Graphics;

import tm.Game;
import tm.ImageCache;
import tm.completable.Completable;
import tm.completable.InstantCompletable;

public class AddTerraformingRatingAction implements Action {
    private final int delta;

    public AddTerraformingRatingAction() {
        this(1);
    }

    public AddTerraformingRatingAction(int delta) {
        this.delta = delta;
    }

    @Override
    public Completable begin(Game game) {
        return new InstantCompletable(game) {
            @Override
            public void complete() {
                game.getCurrentPlayer().adjustRating(delta);
            }

            @Override
            public void undo() {
                game.getCurrentPlayer().adjustRating(-delta);
            }

            @Override
            public void redo() {
                game.getCurrentPlayer().adjustRating(delta);
            }
        };
    }

    @Override
    public void render(Graphics g, int x, int y) {
        g.drawImage(ImageCache.getImage("images/icon_tr.png"), x, y, null);
    }
}
