package tm;

public class SwitchRoundAction extends InstantAction {

	private final Game game;
	
	protected SwitchRoundAction(final Game game) {
		super(game);
		this.game = game;
	}

	@Override
	public boolean check() {
		return true;
	}

	@Override
	public void complete() {
		game.getPlanet().adjustRound(1);
	}

	@Override
	public void undo() {
		game.getPlanet().adjustRound(-1);
	}

	@Override
	public void redo() {
		game.getPlanet().adjustRound(1);
	}
}
