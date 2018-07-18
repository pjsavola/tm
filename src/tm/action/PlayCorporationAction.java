package tm.action;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import tm.Card;
import tm.Corporation;
import tm.Game;
import tm.completable.Completable;
import tm.completable.SelectCardsCompletable;

public class PlayCorporationAction implements Action {

    private Corporation corporation;

    @Override
    public Completable begin(Game game) {
        final Deque<Card> deck = game.getCorporationDeck();
        final List<Card> corporations = new ArrayList<>(deck);
        return new SelectCardsCompletable(game, corporations) {

            @Override
            public int maxSelection() {
                return 1;
            }

            @Override
            public boolean check() {
                if (selectedCards.isEmpty()) {
                    System.err.println ("You must select one of the corporations");
                    return false;
                }
                corporation = (Corporation) selectedCards.iterator().next();
                if (!corporation.start(game)) {
                    System.err.println("Not enough money to pay for the initial cards");
                    return false;
                }
                return true;
            }

            @Override
            public void complete() {
                game.getCurrentPlayer().addTags(corporation.getTags());
                game.getCurrentPlayer().setCorporation(corporation);
                cancel();
            }

            @Override
            public void undo() {
                game.getCurrentPlayer().removeTags(corporation.getTags());
                game.getCurrentPlayer().setCorporation(null);
                game.repaint();
            }

            @Override
            public void redo() {
                throw new UnsupportedOperationException();
            }

            @Override
            public String getTitle() {
                return "Select your corporation";
            }
        };

    }
}
