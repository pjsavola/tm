package tm.action;

import java.util.ArrayList;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
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
        final List<Card> hand = new ArrayList<>(game.getCurrentPlayer().getCards());
        return new SelectCardsCompletable(game, game.getCurrentPlayer().getCards()) {

            @Override
            public int maxSelection() {
                return 1;
            }

            @Override
            public boolean check() {
                if (selectedCards.isEmpty()) {
                    System.err.println ("You must select a card to play it");
                    return false;
                }
                final int cost = selectedCards.iterator().next().getCost();
                final Action paymentAction = new ResourceDeltaAction(new Resources(-cost));
                if (paymentAction.check(game)) {
                    game.getActionHandler().addPendingAction(paymentAction);
                    return true;
                } else {
                    System.err.println("Not enough money to pay for the card");
                    return false;
                }
            }

            @Override
            public void complete() {
                game.getCurrentPlayer().getCards().removeAll(selectedCards);
                cancel();
            }

            @Override
            public void undo() {
                game.getCurrentPlayer().getCards().clear();
                game.getCurrentPlayer().getCards().addAll(hand);
                game.repaint();
            }

            @Override
            public void redo() {
                game.getCurrentPlayer().getCards().removeAll(selectedCards);
                game.repaint();
            }

            @Override
            public String getTitle() {
                return "Select card to play";
            }
        };
    }
}
