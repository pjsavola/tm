package tm;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddOxygenAction;
import tm.action.AddTemperatureAction;
import tm.action.AddWaterAction;
import tm.action.DiscardAction;
import tm.action.IncomeDeltaAction;
import tm.action.PlaceTileAction;
import tm.action.PlayCardAction;
import tm.action.ResourceDeltaAction;
import tm.action.SwitchRoundAction;
import tm.completable.Completable;

public class ActionPool {
	private static final Font font = new Font("Arial", Font.BOLD, 12);
	private final Game game;
	private final List<ActionChain> standardActions = new ArrayList<>();
	private final List<Action> actions = new ArrayList<>();
	private static final Color ENABLED_COLOR = new Color(0xFFFFFF);
	private static final Color DISABLED_COLOR = new Color(0x666666);
	
	public ActionPool(final Game game) {
		this.game = game;
		standardActions.add(new ActionChain('d', "Discard",
			new DiscardAction()));
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
		standardActions.add(new ActionChain('x', "Play card",
			new PlayCardAction()));
	}
	
	public Completable getCompletable(final char c) {
		for (final Action action : standardActions) {
			if (action.getKey() == c && action.check(game)) {
				return action.begin(game);
			}
		}
		for (final Action action : actions) {
			if (action.getKey() == c && action.check(game)) {
				return action.begin(game);
			}
		}
		return null;
	}
	
	public void render(final Graphics g) {
    	final Color oldColor = g.getColor();
		g.setFont(font);
		int i = 11;
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
