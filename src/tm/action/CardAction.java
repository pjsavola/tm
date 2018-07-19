package tm.action;

import tm.Game;
import tm.completable.Completable;
import tm.completable.InstantCompletable;

public abstract class CardAction implements Action {

    private int usedOnRound;
    private final boolean undoable;

    public CardAction(boolean undoable) {
        this.undoable = undoable;
    }

    @Override
    public boolean check(Game game) {
                return usedOnRound < game.getPlanet().getRound();
            }

    @Override
    public Completable begin(Game game) {
        return new InstantCompletable(game) {
            @Override
            public void complete() {
                usedOnRound = game.getPlanet().getRound();
                if (undoable) {
                    game.getActionHandler().addPendingAction(getAction(game));
                } else {
                    game.getActionHandler().addPendingIrreversibleAction(getAction(game));
                }
            }

            @Override
            public void undo() {
                usedOnRound = game.getPlanet().getRound() - 1;
            }

            @Override
            public void redo() {
                usedOnRound = game.getPlanet().getRound();
            }
        };
    }

    protected abstract Action getAction(Game game);
}
