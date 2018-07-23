package tm.action;

import tm.Game;
import tm.Resources;
import tm.completable.Completable;
import tm.completable.InstantCompletable;

public class AddTemperatureAction implements Action {

    @Override
    public boolean check(Game game) {
        return game.getPlanet().getTemperature() < 8;
    }

    @Override
    public Completable begin(Game game) {
        return new InstantCompletable(game) {
            @Override
            public void complete() {
                game.getPlanet().adjustTemperature(2);
                game.getCurrentPlayer().adjustRating(1);
                if (game.getPlanet().getTemperature() == -26 || game.getPlanet().getTemperature() == -22) {
                    final Action bonusAction = new IncomeDeltaAction(Resources.HEAT);
                    if (bonusAction.check(game)) {
                        game.getActionHandler().addPendingAction(bonusAction);
                    }
                }
                if (game.getPlanet().getTemperature() == 0) {
                    if (game.getPlanet().getWaterCount() > 0) {
                        game.getActionHandler().addPendingAction(new AddWaterAction());
                    }
                }
            }

            @Override
            public void undo() {
                game.getPlanet().adjustTemperature(-2);
                game.getCurrentPlayer().adjustRating(-1);
            }

            @Override
            public void redo() {
                game.getPlanet().adjustTemperature(2);
                game.getCurrentPlayer().adjustRating(1);
            }
        };
    }

    @Override
    public boolean isOptional() {
        return true;
    }
}
