package tm.action;

import java.util.ArrayList;

import tm.Game;
import tm.completable.Completable;
import tm.completable.SelectCardsCompletable;

public class PlayCardAction implements Action {

    @Override
    public char getKey() {
        return 'x';
    }

    @Override
    public boolean check(final Game game) {
        return !game.getCurrentPlayer().getCards().isEmpty();
    }

    @Override
    public Completable begin(final Game game) {
        return new SelectCardsCompletable(game, new ArrayList<>(game.getCurrentPlayer().getCards()), SelectCardsCompletable.Type.PLAY);
    }
}
