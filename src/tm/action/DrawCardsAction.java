package tm.action;

import java.util.ArrayList;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.completable.Completable;
import tm.completable.SelectCardsCompletable;

public class DrawCardsAction implements Action {

    final int amount;
    final boolean choice;
    final boolean initial;

    public DrawCardsAction(final int amount, final boolean choice, final boolean initial) {
        this.amount = amount;
        this.choice = choice;
        this.initial = initial;
    }

    @Override
    public Completable begin(final Game game) {
        final List<Card> drawnCards = new ArrayList<>(amount);
        while (drawnCards.size() < amount) {
            drawnCards.add(game.drawCard());
        }
        if (choice) {
            return new SelectCardsCompletable(game, drawnCards) {

                @Override
                public boolean check() {
                    final Action action = new ResourceDeltaAction(new Resources(-3 * selectedCards.size()));
                    if (initial || action.check(game)) {
                        game.getActionHandler().addPendingAction(action);
                        return true;
                    } else {
                        System.err.println("Not enough money to keep the cards");
                        return false;
                    }
                }

                @Override
                public void complete() {
                    game.getCurrentPlayer().getCards().addAll(selectedCards);
                    if (initial) {
                        game.getActionHandler().addPendingAction(new PlayCorporationAction());
                    }
                    cancel();
                }

                @Override
                public void undo() {
                    game.getCurrentPlayer().getCards().removeAll(selectedCards);
                    game.getActionHandler().reprocess(this);
                    game.addMouseListener(mouseListener);
                    game.repaint();
                }

                @Override
                public void redo() {
                    game.getCurrentPlayer().getCards().addAll(selectedCards);
                    cancel();
                }

                @Override
                public String getTitle() {
                    return "Select cards to keep";
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
                    game.addMouseListener(mouseListener);
                    game.repaint();
                }

                @Override
                public void redo() {
                    game.getCurrentPlayer().getCards().addAll(selection);
                    cancel();
                }

                @Override
                public String getTitle() {
                    return "You got these new cards";
                }
            };
        }
    }
}