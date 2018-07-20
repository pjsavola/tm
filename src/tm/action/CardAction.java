package tm.action;

import tm.Game;
import tm.completable.Completable;

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
        return new CardActionCompletable(game, this);
    }

    protected abstract Action getAction(Game game);

    protected static class CardActionCompletable implements Completable {
        private final CardAction action;
        private final Game game;

        protected CardActionCompletable(Game game, CardAction action) {
            this.game = game;
            this.action = action;
            game.getActionHandler().completed(this);
        }

        @Override
        public void complete() {
            action.usedOnRound = game.getPlanet().getRound();
            if (action.undoable) {
                game.getActionHandler().addPendingAction(action.getAction(game));
            } else {
                game.getActionHandler().addPendingIrreversibleAction(action.getAction(game));
            }
        }

        @Override
        public void undo() {
            action.usedOnRound = game.getPlanet().getRound() - 1;
        }

        @Override
        public void redo() {
            action.usedOnRound = game.getPlanet().getRound();
        }
    }
}
