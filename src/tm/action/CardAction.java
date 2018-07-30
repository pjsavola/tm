package tm.action;

import com.sun.istack.internal.Nullable;
import tm.ActionType;
import tm.Game;
import tm.completable.Completable;

public abstract class CardAction implements Action {

    private int usedOnRound;
    private final boolean undoable;
    @Nullable
    private CardAction alternativeAction;
    private final ActionType type;

    public CardAction(boolean undoable, ActionType type) {
        this.undoable = undoable;
        this.type = type;
    }

    @Override
    public ActionType getType() {
        return type;
    }

    @Override
    public boolean isUndoable() {
        return undoable;
    }

    public void setAlternativeAction(CardAction action) {
        alternativeAction = action;
    }

    @Override
    public boolean check(Game game) {
        final int round = game.getPlanet().getRound();
        if (usedOnRound >= round) {
            return false;
        }
        if (alternativeAction != null && alternativeAction.usedOnRound >= round) {
            return false;
        }
        final Action cardAction = getAction(game);
        return cardAction.check(game);
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
            final Action cardAction = action.getAction(game);
            action.usedOnRound = game.getPlanet().getRound();
            game.getActionHandler().addPendingAction(cardAction);
        }

        @Override
        public void undo() {
            if (action.isUndoable()) {
                action.usedOnRound = game.getPlanet().getRound() - 1;
            }
        }

        @Override
        public void redo() {
            if (action.isUndoable()) {
                action.usedOnRound = game.getPlanet().getRound();
            }
        }
    }
}
