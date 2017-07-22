package tm.action;

import tm.Game;
import tm.completable.Completable;
import tm.completable.SelectCardsCompletable;

public class DiscardAction implements Action {

    @Override
    public boolean check(final Game game) {
        return !game.getCurrentPlayer().getCards().isEmpty();
    }

    @Override
    public Completable begin(final Game game) {
        return new SelectCardsCompletable(game, game.getCurrentPlayer().getCards(), SelectCardsCompletable.Type.DISCARD);
    }
}
