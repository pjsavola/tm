package tm.action;

import java.util.ArrayList;
import java.util.List;

import tm.Card;
import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.completable.Completable;
import tm.completable.SelectCardsCompletable;

public class PlayCorporationAction implements Action {

    @Override
    public Completable begin(final Game game) {
        final List<Card> corporations = new ArrayList<>(2);
        corporations.add(game.drawCorporation());
        corporations.add(game.drawCorporation());
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
                // Check for paying the initial cards.
                return true;
            }

            @Override
            public void complete() {
                final Action initialResources = new ResourceDeltaAction(new Resources(60));
                game.getActionHandler().addPendingAction(initialResources);
                game.getCurrentPlayer().setCorporation((Corporation) selectedCards.iterator().next());
                cancel();
            }

            @Override
            public void undo() {
                game.getCurrentPlayer().setCorporation(null);
                game.repaint();
            }

            @Override
            public void redo() {
                game.getCurrentPlayer().setCorporation((Corporation) selectedCards.iterator().next());
                game.repaint();
            }

            @Override
            public String getTitle() {
                return "Select your corporation";
            }
        };

    }
}
