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

    @Override
    public Completable begin(Game game) {
        final Deque<Card> deck = game.getCorporationDeck();
        final List<Card> corporations = new ArrayList<>(deck);
        return new SelectCardsCompletable(game, corporations, 1, 1, "Select your corporation") {

            private Card getSelectedCard() {
                return selectedCards.iterator().next();
            }

            @Override
            public boolean check() {
                return game.getCurrentPlayer().canAdjustResources(getSelectedCard().getResourceDelta(game));
            }

            @Override
            public void complete() {
                final Corporation corporation = (Corporation) getSelectedCard();
                final Action action = corporation.getInitialAction(game);
                if (action != null) {
                    game.getActionHandler().addPendingAction(corporation.getInitialAction(game));
                }
                game.getCurrentPlayer().adjustResources(corporation.getResourceDelta(game));
                game.getCurrentPlayer().addTags(corporation.getTags());
                game.getCurrentPlayer().corporation = corporation;
                game.repaint();
            }

            @Override
            public void undo() {
                final Corporation corporation = (Corporation) getSelectedCard();
                game.getCurrentPlayer().adjustResources(corporation.getResourceDelta(game).negate());
                game.getCurrentPlayer().removeTags(corporation.getTags());
                game.getCurrentPlayer().corporation = null;
                game.repaint();
            }

            @Override
            public void redo() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
