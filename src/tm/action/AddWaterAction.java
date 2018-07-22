package tm.action;

import tm.Game;
import tm.Tile;
import tm.completable.Completable;
import tm.completable.InstantCompletable;

public class AddWaterAction implements Action {

    @Override
    public boolean check(Game game) {
        return game.getPlanet().getWaterCount() > 0;
    }

    @Override
    public Completable begin(Game game) {
        return new InstantCompletable(game) {
            @Override
            public void complete() {
                game.getPlanet().adjustWaterCount(-1);
                game.getCurrentPlayer().adjustRating(1);
                game.getActionHandler().addPendingAction(new PlaceTileAction(Tile.Type.WATER));
            }

            @Override
            public void undo() {
                game.getPlanet().adjustWaterCount(1);
                game.getCurrentPlayer().adjustRating(-1);
            }

            @Override
            public void redo() {
                game.getPlanet().adjustWaterCount(-1);
                game.getCurrentPlayer().adjustRating(1);
            }
        };
    }

    @Override
    public boolean isOptional() {
        return true;
    }
}
