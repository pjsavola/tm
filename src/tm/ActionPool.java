package tm;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionPool {
	private final static Font font = new Font("Arial", Font.BOLD, 12);
	private final Game game;
	
	public ActionPool(final Game game) {
		this.game = game;
	}
	
	public Map<Character, Action> getActionMap() {
		final Map<Character, Action> actionMap = new HashMap<>();
		List<Action> actions = new ArrayList<>();
		actions.add(new ActionChain('e',
			new ResourceDeltaAction(new Resources(-11), game),
			new IncomeDeltaAction(new Resources(0, 0, 0, 0, 1, 0), game)));
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
		actions.add(new DrawAndKeepAction(10, game));
		actions.stream()
		       .filter(action -> action.check())
		       .forEach(action -> actionMap.put(action.getKey(), action));
		return actionMap;
	}
	
	public void render(final Graphics g) {
    	final Color oldColor = g.getColor();
		g.setFont(font);
		renderText(g, "(e)nergy", 9);
		renderText(g, "(m)eteorite", 8);
		renderText(g, "(w)ater", 7);
		renderText(g, "(g)reenery", 6);
		renderText(g, "(c)ity", 5);
		renderText(g, "(p)ass", 4);
		renderText(g, "(u)ndo", 2);
		renderText(g, "(r)edo", 1);
		g.setColor(oldColor);
	}
	
	private static void renderText(final Graphics g, final String text, final int i) {
		g.setColor(new Color(0xFFFFFF));
		final FontMetrics metrics = g.getFontMetrics();
		int w = metrics.stringWidth(text);
        int h = metrics.getHeight();
        g.drawString(text, 698 - w, 698 - h * i);
	}
}
