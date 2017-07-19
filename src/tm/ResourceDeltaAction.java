package tm;

public class ResourceDeltaAction extends InstantAction {

	private static final long serialVersionUID = 1L;
	final Resources delta;
	final Player player;
	
	public ResourceDeltaAction(final Resources delta, final Game game) {
		super(game);
		this.delta = delta;
		this.player = game.getCurrentPlayer();
	}
	
	@Override
	public boolean check() {
		return player.canAdjustResources(delta);
	}

	@Override
	public void complete() {
		player.adjustResources(delta);
	}

	@Override
	public void undo() {
		player.adjustResources(delta.negate());
	}

	@Override
	public void redo() {
		player.adjustResources(delta);
	}
}
