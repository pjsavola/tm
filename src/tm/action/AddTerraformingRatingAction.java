package tm.action;

import tm.Game;
import tm.completable.Completable;
import tm.completable.InstantCompletable;

public class AddTerraformingRatingAction implements Action {
    @Override
    public Completable begin(Game game) {
        return new InstantCompletable(game) {
            @Override
            public void complete() {
                game.getCurrentPlayer().adjustRating(1);
            }

            @Override
            public void undo() {
                game.getCurrentPlayer().adjustRating(-1);
            }

            @Override
            public void redo() {
                game.getCurrentPlayer().adjustRating(1);
            }
        };
    }
}
