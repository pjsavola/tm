package tm.action;

import java.util.ArrayList;

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
        // Maybe does not need a copy?
        return new SelectCardsCompletable(game, new ArrayList<>(game.getCurrentPlayer().getCards()), SelectCardsCompletable.Type.DISCARD);
    }
}
