package tm.action;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

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
    public Point render(Graphics g, int x, int y, Game game) {
        final BufferedImage image = ImageCache.getImage("images/big_icon_tr.png");
        int currentX = x;
        for (int i = 0; i < delta; i++) {
            if (i != 0) {
                currentX += 4; // spacing
            }
            g.drawImage(image, currentX, y, null);
            currentX += image.getWidth();
        }
        return new Point(currentX, y + image.getHeight());
    }
}
