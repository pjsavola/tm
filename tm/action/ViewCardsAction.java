package tm.action;

import java.util.List;

import tm.Card;
import tm.Game;
import tm.completable.Completable;
import tm.completable.SelectItemsCompletable;

public class ViewCardsAction implements Action {

    private final List<Card> cards;
    private final String title;

    public ViewCardsAction(List<Card> cards, String title) {
        this.cards = cards;
        this.title = title;
    }

    @Override
    public Completable begin(Game game) {
        return new SelectItemsCompletable<Card>(game, cards, 0, 0, title) {
            @Override
            public boolean check() {
                return false;
            }
            @Override
            public void complete() {
            }
            @Override
            public void undo() {
            }
            @Override
            public void redo() {
            }
        };
    }
}
