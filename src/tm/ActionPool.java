package tm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionPool {
	private final Game game;
	
	public ActionPool(final Game game) {
		this.game = game;
	}
	
	public Map<Character, Action> getActionMap() {
		final Map<Character, Action> actionMap = new HashMap<>();
		List<Action> actions = new ArrayList<>();
		actions.add(new ActionChain('m',
			new ResourceDeltaAction(new Resources(-14), game),
			new AddTemperatureAction(game)));
		actions.add(new ActionChain('w',
			new ResourceDeltaAction(new Resources(-18), game),
			new PlaceTileAction(Tile.Type.WATER, game),
			new AddWaterAction(game)));
		actions.add(new ActionChain('g',
			new ResourceDeltaAction(new Resources(-23), game),
			new PlaceTileAction(Tile.Type.GREENERY, game),
			new AddOxygenAction(game)));
		actions.add(new ActionChain('c',
			new ResourceDeltaAction(new Resources(-25), game),
			new PlaceTileAction(Tile.Type.CITY, game),
			new IncomeDeltaAction(new Resources(1), game)));
		actions.add(new ActionChain('p',
			new ResourceDeltaAction(game.getCurrentPlayer().getIncome(), game),
			new SwitchRoundAction(game)));
		actions.stream()
		       .filter(action -> action.check())
		       .forEach(action -> actionMap.put(action.getKey(), action));
		return actionMap;
	}
}
