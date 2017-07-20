package tm;

public class ResourceDeltaAction implements Action {

	final Resources delta;
	
	public ResourceDeltaAction(final Resources delta) {
		this.delta = delta;
	}
	
	@Override
	public char getKey() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean check(final Game game) {
		return game.getCurrentPlayer().canAdjustResources(delta);
	}

	@Override
	public Completable begin(final Game game) {
		return new InstantCompletable(game) {
			@Override
			public void complete() {
				game.getCurrentPlayer().adjustResources(delta);
			}

			@Override
			public void undo() {
				game.getCurrentPlayer().adjustResources(delta.negate());
			}

			@Override
			public void redo() {
				game.getCurrentPlayer().adjustResources(delta);
			}
		};
	}
}
