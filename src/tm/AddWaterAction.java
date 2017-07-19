package tm;

public class AddWaterAction extends InstantAction {
	
	private static final long serialVersionUID = 1L;
	private final Planet planet;
	private final Player player;
	
	public AddWaterAction(final Game game) {
		super(game);
		this.planet = game.getPlanet();
		this.player = game.getCurrentPlayer();
	}
	
	@Override
	public boolean check() {
		return planet.getWaterCount() > 0;
	}

	@Override
	public void complete() {
		planet.adjustWaterCount(-1);
		player.adjustRating(1);
	}

	@Override
	public void undo() {
		planet.adjustWaterCount(1);
		player.adjustRating(-1);
	}

	@Override
	public void redo() {
		planet.adjustWaterCount(-1);
		player.adjustRating(1);
	}
}
