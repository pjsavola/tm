package tm.action;

import java.util.ArrayList;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.completable.Completable;
import tm.completable.SelectCardsCompletable;

public class DiscardAction implements Action {

    @Override
    public boolean check(Game game) {
        return !game.getCurrentPlayer().getCards().isEmpty();
    }

    @Override
    public Completable begin(Game game) {
        final List<Card> hand = new ArrayList<>(game.getCurrentPlayer().getCards());
        return new SelectCardsCompletable(game, hand, 1, hand.size(), "Select cards to discard") {

            @Override
            public void complete() {
                game.getActionHandler().addPendingAction(new ResourceDeltaAction(new Resources(selectedCards.size())));
                game.getCurrentPlayer().getCards().removeAll(selectedCards);
                game.getDiscardDeck().addAll(selectedCards);
                game.repaint();
            }

            @Override
            public void undo() {
                game.getCurrentPlayer().getCards().addAll(selectedCards);
                game.getDiscardDeck().removeAll(selectedCards);
                game.repaint();
            }

            @Override
            public void redo() {
                game.getCurrentPlayer().getCards().removeAll(selectedCards);
                game.getDiscardDeck().addAll(selectedCards);
                game.repaint();
            }
        };
    }
}
