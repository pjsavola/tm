package tm.action;

import tm.Game;
import tm.Planet;
import tm.completable.Completable;
import tm.completable.InstantCompletable;

public class SwitchRoundAction implements Action {

    @Override
    public Completable begin(Game game) {
        return new InstantCompletable(game) {
            @Override
            public void complete() {
                game.getCurrentPlayer().adjustResources(game.getCurrentPlayer().getTurnIncome());
                final Planet planet = game.getPlanet();
                if (planet.getRound() >= 14) {
                    game.gameOver();
                    return;
                }
                if (planet.getOxygen() >= 14 && planet.getWaterRemaining() <= 0 && planet.getTemperature() >= 8) {
                    game.gameOver();
                    return;
                }
                planet.adjustRound(1);
                game.getActionHandler().addPendingAction(new DrawCardsAction(4, true, false));
                game.getCurrentPlayer().saveRating();
            }

            @Override
            public void undo() {
                // Cannot end up here because card drawing is irreversible
                throw new UnsupportedOperationException();
            }

            @Override
            public void redo() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
