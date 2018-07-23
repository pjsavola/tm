package tm.action;

import tm.completable.Completable;
import tm.Game;
import tm.completable.InstantCompletable;

public class SwitchRoundAction implements Action {

    @Override
    public Completable begin(Game game) {
        return new InstantCompletable(game) {
            @Override
            public void complete() {
                game.getCurrentPlayer().adjustResources(game.getCurrentPlayer().getIncome());
                game.getPlanet().adjustRound(1);
                game.getActionHandler().addPendingIrreversibleAction(new DrawCardsAction(4, true, false));
                game.getCurrentPlayer().saveRating();
            }

            @Override
            public void undo() {
                // Cannot end up here because card drawing is irreversible
                throw new UnsupportedOperationException();
            }

            @Override
            public void redo() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
