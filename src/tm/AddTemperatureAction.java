package tm;

import java.awt.Graphics;

public class AddTemperatureAction implements Action {

	private final Game game;
	private final Planet planet;
	private final Player player;
	private int temperatureDelta;
	private int ratingIncrease;
	private boolean bonusHeat;
	
	public AddTemperatureAction(final Game game) {
		this.game = game;
		planet = game.getPlanet();
		player = game.getCurrentPlayer();
	}
	
	@Override
	public boolean check() {
		return true;
	}

	@Override
	public void complete() {
		final boolean bonusWater = planet.getTemperature() == -2 && planet.getWaterCount() > 0;
		bonusHeat = planet.getTemperature() == -26 || planet.getTemperature() == -22;
		temperatureDelta = planet.adjustTemperature(2);
		ratingIncrease = temperatureDelta / 2;
		if (bonusWater) {
			game.getActionHandler().addPendingAction(new PlaceTileAction(Tile.Type.WATER, game));
			ratingIncrease++;
		}
		player.adjustRating(ratingIncrease);
		if (bonusHeat) {
			player.adjustIncome(new Resources(0, 0, 0, 0, 0, 1));
		}
	}

	@Override
	public void undo() {
		planet.adjustTemperature(-temperatureDelta);
		player.adjustRating(-ratingIncrease);
		if (bonusHeat) {
			player.adjustIncome(new Resources(0, 0, 0, 0, 0, -1));
		}
	}

	@Override
	public void redo() {
		planet.adjustTemperature(temperatureDelta);
		player.adjustRating(ratingIncrease);
		if (bonusHeat) {
			player.adjustIncome(new Resources(0, 0, 0, 0, 0, 1));
		}
	}

	@Override
	public char getKey() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void begin() {
		game.getActionHandler().actionFinished(this);		
	}

	@Override
	public void cancel() {
	}

	@Override
	public void paint(Graphics g) {
	}
}
