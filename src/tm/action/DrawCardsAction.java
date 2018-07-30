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
            return new SelectCardsCompletable(game, drawnCards) {
                private List<Card> discardedCards;

                @Override
                public int maxSelection() {
                    return max;
                }

                @Override
                public boolean check() {
                    if (selectedCards.size() < min) {
                        System.err.println("You should select at least " + min + " cards");
                        return false;
                    }
                    if (selectedCards.size() > max) {
                        System.err.println("You should select at most " + max + " cards");
                        return false;
                    }
                    if (pay) {
                        final Action action = new ResourceDeltaAction(new Resources(-3 * selectedCards.size()));
                        if (initial || action.check(game)) {
                            game.getActionHandler().addPendingAction(action);
                        } else {
                            System.err.println("Not enough money to keep the cards");
                            return false;
                        }
                    }
                    return true;
                }

                @Override
                public void complete() {
                    discardedCards = new ArrayList<>(selection);
                    discardedCards.removeAll(selectedCards);
                    game.getCurrentPlayer().getCards().addAll(selectedCards);
                    game.getDiscardDeck().addAll(discardedCards);
                    if (initial) {
                        game.getActionHandler().addPendingAction(new PlayCorporationAction());
                    }
                    cancel();
                }

                @Override
                public void undo() {
                    game.getCurrentPlayer().getCards().removeAll(selectedCards);
                    game.getDiscardDeck().removeAll(discardedCards);
                    game.getActionHandler().reprocess(this);
                    game.repaint();
                }

                @Override
                public void redo() {
                    game.getCurrentPlayer().getCards().addAll(selectedCards);
                    game.getDiscardDeck().addAll(discardedCards);
                    cancel();
                }

                @Override
                public String getTitle() {
                    if (min == max) {
                        return "Select " + min + " cards to keep";
                    } else {
                        return "Select " + min + "-" + max + " cards to keep";
                    }
                }
            };
        } else {
            return new SelectCardsCompletable(game, drawnCards) {

                @Override
                public int maxSelection() {
                    return 0;
                }

                @Override
                public boolean check() {
                    return true;
                }

                @Override
                public void complete() {
                    game.getCurrentPlayer().getCards().addAll(selection);
                    cancel();
                }

                @Override
                public void undo() {
                    game.getCurrentPlayer().getCards().removeAll(selection);
                    game.getActionHandler().reprocess(this);
                    game.repaint();
                }

                @Override
                public void redo() {
                    game.getCurrentPlayer().getCards().addAll(selection);
                    cancel();
                }

                @Override
                public String getTitle() {
                    return "You got these cards";
                }
            };
        }
    }
}
