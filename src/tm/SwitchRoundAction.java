package tm;

public class SwitchRoundAction implements Action {

	@Override
	public char getKey() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean check(final Game game) {
		return true;
	}

	@Override
	public Completable begin(final Game game) {
		return new InstantCompletable(game) {
			@Override
			public void complete() {
				game.getCurrentPlayer().adjustResources(game.getCurrentPlayer().getIncome());
				game.getPlanet().adjustRound(1);
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
