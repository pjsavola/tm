package tm.action;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.ImageCache;
import tm.Resources;
import tm.completable.Completable;
import tm.completable.SelectItemsCompletable;

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
            return new SelectItemsCompletable<Card>(game, drawnCards, min, max, title) {
                private List<Card> discardedCards;

                @Override
                public boolean check() {
                    if (pay && !initial && !game.getCurrentPlayer().canAdjustResources(new Resources(-3 * selectedItems.size()))) {
                        return false;
                    }
                    return true;
                }

                @Override
                public void complete() {
                    if (pay) {
                        game.getCurrentPlayer().adjustResources(new Resources(-3 * selectedItems.size()));
                    }
                    discardedCards = new ArrayList<>(selection);
                    discardedCards.removeAll(selectedItems);
                    game.getCurrentPlayer().getCards().addAll(selectedItems);
                    game.getDiscardDeck().addAll(discardedCards);
                    if (initial) {
                        game.getActionHandler().addPendingAction(new PlayCardAction(game.getCorporationDeck(), "Select your corporation"));
                    }
                }

                @Override
                public void undo() {
                    if (pay) {
                        game.getCurrentPlayer().adjustResources(new Resources(3 * selectedItems.size()));
                    }
                    game.getCurrentPlayer().getCards().removeAll(selectedItems);
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
            return new SelectItemsCompletable<Card>(game, drawnCards, 0, 0, "You got these cards") {
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

    @Override
    public void render(Graphics g, int x, int y, Game game) {
        final String str = pay ? "Buy" : "Draw";
        g.setColor(Color.WHITE);
        g.drawString(str, x, y + 8);
        for (int i = 0; i < amount; i++) {
            g.drawImage(ImageCache.getImage("images/icon_card.png"), x + i * 16, y + 12, null);
            if (i + 1 > max) {
                g.setColor(Color.RED);
                g.drawLine(x + i * 16 + 2, y + 12, x + i * 16 + 10, y + 28);
                g.drawLine(x + i * 16 + 2, y + 28, x + i * 16 + 10, y + 12);
            }
        }
    }
}
