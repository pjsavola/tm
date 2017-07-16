package tm;

public class AddOxygenAction extends InstantAction {

	private int ratingIncrease;
	private final Planet planet;
	private final Player player;
	private boolean bonusTemperature;
	
	public AddOxygenAction(final Game game) {
		super(game);
		planet = game.getPlanet();
		player = game.getCurrentPlayer();
	}
	
	@Override
	public boolean check() {
		return true;
	}

	@Override
	public void complete() {
		bonusTemperature = planet.getOxygen() == 7 && planet.getTemperature() < 8; 
		ratingIncrease = planet.adjustOxygen(1);
		if (bonusTemperature) {
			ratingIncrease += planet.adjustTemperature(2) / 2;
		}
		player.adjustRating(ratingIncrease);
	}

	@Override
	public void undo() {
		planet.adjustOxygen(-ratingIncrease);
		player.adjustRating(-ratingIncrease);
		if (bonusTemperature) {
			planet.adjustTemperature(-2);
		}
	}

	@Override
	public void redo() {
		planet.adjustOxygen(ratingIncrease);
		player.adjustRating(ratingIncrease);
		if (bonusTemperature) {
			planet.adjustTemperature(2);
		}
	}
}
