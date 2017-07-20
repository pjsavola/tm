package tm;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class ActionPool {
	private final static Font font = new Font("Arial", Font.BOLD, 12);
	private final Game game;
	private final List<ActionChain> standardActions = new ArrayList<>();
	private final static Color ENABLED_COLOR = new Color(0xFFFFFF);
	private final static Color DISABLED_COLOR = new Color(0x666666);
	
	public ActionPool(final Game game) {
		this.game = game;
		standardActions.add(new ActionChain('e', "Energy income",
			new ResourceDeltaAction(new Resources(-11)),
			new IncomeDeltaAction(new Resources(0, 0, 0, 0, 1, 0))));
		standardActions.add(new ActionChain('m', "Temperature",
			new ResourceDeltaAction(new Resources(-14)),
			new AddTemperatureAction()));
		standardActions.add(new ActionChain('w', "Water",
			new ResourceDeltaAction(new Resources(-18)),
			new PlaceTileAction(Tile.Type.WATER),
			new AddWaterAction()));
		standardActions.add(new ActionChain('g', "Greenery",
			new ResourceDeltaAction(new Resources(-23)),
			new PlaceTileAction(Tile.Type.GREENERY),
			new AddOxygenAction()));
		standardActions.add(new ActionChain('c', "City",
			new ResourceDeltaAction(new Resources(-25)),
			new PlaceTileAction(Tile.Type.CITY),
			new IncomeDeltaAction(new Resources(1))));
		standardActions.add(new ActionChain('p', "Pass",
			new SwitchRoundAction()));
		//standardActions.add(new DrawAndKeepAction(10, game));
	}
	
	public Completable getCompletable(final char c) {
		for (final Action action : standardActions) {
			if (action.getKey() == c && action.check(game)) {
				return action.begin(game);
			}
		}
		return null;
	}
	
	public void render(final Graphics g) {
    	final Color oldColor = g.getColor();
		g.setFont(font);
		int i = 9;
		for (final ActionChain action : standardActions) {
			g.setColor(action.check(game) ? ENABLED_COLOR : DISABLED_COLOR);
			renderText(g, action.getKey() + ": " + action.getDescription(), i--);
		}
		g.setColor(game.getActionHandler().canUndo() ? ENABLED_COLOR : DISABLED_COLOR);
		renderText(g, "u: Undo", 2);
		g.setColor(game.getActionHandler().canRedo() ? ENABLED_COLOR : DISABLED_COLOR);
		renderText(g, "r: Redo", 1);
		g.setColor(oldColor);
	}
	
	private static void renderText(final Graphics g, final String text, final int i) {
		final FontMetrics metrics = g.getFontMetrics();
		int w = metrics.stringWidth(text);
        int h = metrics.getHeight();
        g.drawString(text, 698 - w, 698 - h * i);
	}
}
