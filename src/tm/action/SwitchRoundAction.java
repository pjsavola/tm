package tm.action;

import tm.completable.Completable;
import tm.Game;
import tm.completable.InstantCompletable;

public class SwitchRoundAction implements Action {

	@Override
	public Completable begin(final Game game) {
		return new InstantCompletable(game) {
			@Override
			public void complete() {
				game.getCurrentPlayer().adjustResources(game.getCurrentPlayer().getIncome());
				game.getPlanet().adjustRound(1);
				game.getActionHandler().addPendingIrreversibleAction(new DrawCardsAction(4, true, false));
			}

			@Override
			public void undo() {
				game.getCurrentPlayer().adjustResources(game.getCurrentPlayer().getIncome().negate());
				game.getPlanet().adjustRound(-1);
			}

			@Override
			public void redo() {
				game.getCurrentPlayer().adjustResources(game.getCurrentPlayer().getIncome());
				game.getPlanet().adjustRound(1);
			}
		};
	}
}
