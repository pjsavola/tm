package tm.action;

import java.util.ArrayList;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.completable.Completable;
import tm.completable.SelectCardsCompletable;

public class DrawCardsAction implements Action {

    final int amount;
    final boolean choice;

    public DrawCardsAction(final int amount, final boolean choice) {
        this.amount = amount;
        this.choice = choice;
    }

    @Override
    public Completable begin(final Game game) {
        final List<Card> drawnCards = new ArrayList<>(amount);
        while (drawnCards.size() < amount) {
            drawnCards.add(game.drawCard());
        }
        game.getActionHandler().setCancelEnabled(false);
        return new SelectCardsCompletable(game, drawnCards, choice ? SelectCardsCompletable.Type.DRAW : SelectCardsCompletable.Type.DRAW_AND_KEEP);
    }
}
