package tm.action;

import tm.completable.Completable;
import tm.Game;
import tm.completable.InstantCompletable;

public class AddOxygenAction implements Action {

	@Override
	public boolean check(final Game game) {
		return game.getPlanet().getOxygen() < 14;
	}

	@Override
	public Completable begin(final Game game) {
		return new InstantCompletable(game) {
			@Override
			public void complete() {
				game.getPlanet().adjustOxygen(1);
				game.getCurrentPlayer().adjustRating(1);
				if (game.getPlanet().getOxygen() == 8) {
					final Action bonusAction = new AddTemperatureAction();
					if (bonusAction.check(game)) {
						game.getActionHandler().addPendingAction(bonusAction);
					}
				}
			}
			
			@Override
			public void undo() {
				game.getPlanet().adjustOxygen(-1);
				game.getCurrentPlayer().adjustRating(-1);
			}
			
			@Override
			public void redo() {
				game.getPlanet().adjustOxygen(1);
				game.getCurrentPlayer().adjustRating(1);
			}
		};
	}

	@Override
	public boolean isOptional() {
		return true;
	}
}
