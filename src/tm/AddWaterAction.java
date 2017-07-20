package tm;

public class AddWaterAction implements Action {
	
	@Override
	public char getKey() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean check(final Game game) {
		return game.getPlanet().getWaterCount() > 0;
	}

	@Override
	public Completable begin(final Game game) {
		return new InstantCompletable(game) {
			@Override
			public void complete() {
				game.getPlanet().adjustWaterCount(-1);
				game.getCurrentPlayer().adjustRating(1);
			}

			@Override
			public void undo() {
				game.getPlanet().adjustWaterCount(1);
				game.getCurrentPlayer().adjustRating(-1);
			}

			@Override
			public void redo() {
				game.getPlanet().adjustWaterCount(-1);
				game.getCurrentPlayer().adjustRating(1);
			}
		};
	}

}
