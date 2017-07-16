package tm;

public class IncomeDeltaAction extends InstantAction {
	
	final Resources delta;
	private final Player player;
	
	public IncomeDeltaAction(final Resources delta, final Game game) {
		super(game);
		this.delta = delta;
		player = game.getCurrentPlayer();
	}

	@Override
	public boolean check() {
		return true;
	}

	@Override
	public void complete() {
		player.adjustIncome(delta);
	}

	@Override
	public void undo() {
		player.adjustIncome(delta.negate());
	}

	@Override
	public void redo() {
		player.adjustIncome(delta);
	}
}
