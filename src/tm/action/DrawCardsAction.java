package tm.action;

import java.util.ArrayList;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.completable.Completable;
import tm.completable.SelectCardsCompletable;

public class DrawCardsAction implements Action {

    final int amount;

    public DrawCardsAction(final int amount) {
        this.amount = amount;
    }

    @Override
    public Completable begin(final Game game) {
        game.getActionHandler().clearUndoStack();
        game.getActionHandler().setCancelEnabled(false);
        final List<Card> drawnCards = new ArrayList<>(amount);
        while (drawnCards.size() < amount) {
            drawnCards.add(game.drawCard());
        }
        // Maybe does not need a copy?
        return new SelectCardsCompletable(game, drawnCards, SelectCardsCompletable.Type.DRAW);
    }
}
