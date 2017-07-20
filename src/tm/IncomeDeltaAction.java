package tm;

public class IncomeDeltaAction implements Action {
	
	final Resources delta;
	
	public IncomeDeltaAction(final Resources delta) {
		this.delta = delta;
	}
	
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
				game.getCurrentPlayer().adjustIncome(delta);
			}

			@Override
			public void undo() {
				game.getCurrentPlayer().adjustIncome(delta.negate());
			}

			@Override
			public void redo() {
				game.getCurrentPlayer().adjustIncome(delta);
			}
		};
	}
}
