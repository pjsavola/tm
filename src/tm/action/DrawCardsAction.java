package tm.action;

import java.util.ArrayList;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.completable.Completable;
import tm.completable.SelectCardsCompletable;

public class DrawCardsAction implements Action {

    private final int amount;
    private int min;
    private int max;
    private final boolean initial;
    private final boolean pay;

    public DrawCardsAction(int amount, boolean choice, boolean initial) {
        this(amount, choice ? 0 : amount, amount, initial, choice);
    }

    public DrawCardsAction(int amount, int min, int max, boolean initial, boolean pay) {
        this.amount = amount;
        this.min = min;
        this.max = max;
        this.initial = initial;
        this.pay = pay;
    }

    @Override
    public boolean check(Game game) {
        return amount <= 0 || game.canDrawCard();
    }

    @Override
    public boolean isUndoable() {
        return false;
    }

    @Override
    public Completable begin(Game game) {
        final List<Card> drawnCards = new ArrayList<>(amount);
        while (drawnCards.size() < amount) {
            final Card card = game.drawCard();
            if (card == null) {
                // Game ran out of cards!?
                break;
            }
            drawnCards.add(card);
        }
        if (drawnCards.size() < amount) {
            min = Math.min(min, amount);
            max = amount;
        }
        if (min < amount) {
            String title = pay ? "Buy " : "Select ";
            title += min;
            if (min < max) {
                title += "-" + max;
            }
            title += " cards";
            return new SelectCardsCompletable(game, drawnCards, min, max, title) {
                private List<Card> discardedCards;

                @Override
                public boolean check() {
                    if (pay && !initial && !game.getCurrentPlayer().canAdjustResources(new Resources(-3 * selectedCards.size()))) {
                        return false;
                    }
                    return true;
                }

                @Override
                public void complete() {
                    if (pay) {
                        game.getCurrentPlayer().adjustResources(new Resources(-3 * selectedCards.size()));
                    }
                    discardedCards = new ArrayList<>(selection);
                    discardedCards.removeAll(selectedCards);
                    game.getCurrentPlayer().getCards().addAll(selectedCards);
                    game.getDiscardDeck().addAll(discardedCards);
                    if (initial) {
                        game.getActionHandler().addPendingAction(new PlayCardAction(game.getCorporationDeck(), "Select your corporation"));
                    }
                }

                @Override
                public void undo() {
                    if (pay) {
                        game.getCurrentPlayer().adjustResources(new Resources(3 * selectedCards.size()));
                    }
                    game.getCurrentPlayer().getCards().removeAll(selectedCards);
                    game.getDiscardDeck().removeAll(discardedCards);
                    openWindow();
                    game.getActionHandler().reprocess(this);
                    game.repaint();
                }

                @Override
                public void redo() {
                    throw new UnsupportedOperationException();
                }
            };
        } else {
            return new SelectCardsCompletable(game, drawnCards, 0, 0, "You got these cards") {
                @Override
                public void complete() {
                    game.getCurrentPlayer().getCards().addAll(selection);
                    game.repaint();
                }

                @Override
                public void undo() {
                    game.getCurrentPlayer().getCards().removeAll(selection);
                    openWindow();
                    game.getActionHandler().reprocess(this);
                    game.repaint();
                }

                @Override
                public void redo() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
}
