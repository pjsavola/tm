package tm.action;

import java.util.ArrayList;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.completable.Completable;
import tm.completable.SelectItemsCompletable;

public class DiscardAction implements Action {

    @Override
    public boolean check(Game game) {
        return !game.getCurrentPlayer().getCards().isEmpty();
    }

    @Override
    public Completable begin(Game game) {
        final List<Card> hand = new ArrayList<>(game.getCurrentPlayer().getCards());
        return new SelectItemsCompletable<Card>(game, hand, 1, hand.size(), "Select cards to discard") {
            private Resources resources;

            @Override
            public void complete() {
                resources = new Resources(selectedItems.size());
                game.getCurrentPlayer().adjustResources(resources);
                game.getCurrentPlayer().getCards().removeAll(selectedItems);
                game.getDiscardDeck().addAll(selectedItems);
                game.repaint();
            }

            @Override
            public void undo() {
                game.getCurrentPlayer().adjustResources(resources.negate());
                game.getCurrentPlayer().getCards().addAll(selectedItems);
                game.getDiscardDeck().removeAll(selectedItems);
                game.repaint();
            }

            @Override
            public void redo() {
                game.getCurrentPlayer().adjustResources(resources);
                game.getCurrentPlayer().getCards().removeAll(selectedItems);
                game.getDiscardDeck().addAll(selectedItems);
                game.repaint();
            }
        };
    }
}
