package tm.action;

import tm.completable.Completable;
import tm.Game;
import tm.completable.InstantCompletable;
import tm.Resources;
import tm.Tile;

public class AddTemperatureAction implements Action {

	@Override
	public boolean check(final Game game) {
		return game.getPlanet().getTemperature() < 8;
	}
	
	@Override
	public Completable begin(final Game game) {
		return new InstantCompletable(game) {
			@Override
			public void complete() {
				game.getPlanet().adjustTemperature(2);
				game.getCurrentPlayer().adjustRating(1);
				if (game.getPlanet().getTemperature() == -26 || game.getPlanet().getTemperature() == -22) {
					final Action bonusAction = new IncomeDeltaAction(new Resources(0, 0, 0, 0, 0, 1));
					if (bonusAction.check(game)) {
						game.getActionHandler().addPendingAction(bonusAction);	
					}
				}
				if (game.getPlanet().getTemperature() == 0) {
					final Action bonusAction = new ActionChain(
						new AddWaterAction(), new PlaceTileAction(Tile.Type.WATER));
					if (bonusAction.check(game)) {
						game.getActionHandler().addPendingAction(bonusAction);
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
}
