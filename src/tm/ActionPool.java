package tm;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionPool {
	private final static Font font = new Font("Arial", Font.BOLD, 12);
	private final Game game;
	private final List<ActionChain> standardActions = new ArrayList<>();
	private final Color ENABLED_COLOR = new Color(0xFFFFFF);
	private final Color DISABLED_COLOR = new Color(0x666666);
	
	public ActionPool(final Game game) {
		this.game = game;
		standardActions.add(new ActionChain('e', "Energy income",
			new ResourceDeltaAction(new Resources(-11), game),
			new IncomeDeltaAction(new Resources(0, 0, 0, 0, 1, 0), game)));
		standardActions.add(new ActionChain('m', "Temperature",
			new ResourceDeltaAction(new Resources(-14), game),
			new AddTemperatureAction(game)));
		standardActions.add(new ActionChain('w', "Water",
			new ResourceDeltaAction(new Resources(-18), game),
			new PlaceTileAction(Tile.Type.WATER, game),
			new AddWaterAction(game)));
		standardActions.add(new ActionChain('g', "Greenery",
			new ResourceDeltaAction(new Resources(-23), game),
			new PlaceTileAction(Tile.Type.GREENERY, game),
			new AddOxygenAction(game)));
		standardActions.add(new ActionChain('c', "City",
			new ResourceDeltaAction(new Resources(-25), game),
			new PlaceTileAction(Tile.Type.CITY, game),
			new IncomeDeltaAction(new Resources(1), game)));
		standardActions.add(new ActionChain('p', "Pass",
			new ResourceDeltaAction(game.getCurrentPlayer().getIncome(), game),
			new SwitchRoundAction(game)));
		//standardActions.add(new DrawAndKeepAction(10, game));
	}
	
	public Action getAction(final char c) {
		for (final Action action : standardActions) {
			if (action.getKey() == c && action.check()) {
				return clone(action);
			}
		}
		return null;
	}
	
	private static Action clone(final Action action) {
		try {
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			final ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(action);
			final ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			final ObjectInputStream ois = new ObjectInputStream(bais);
			return (Action) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		    return null;
		}
	}
	
	public void render(final Graphics g) {
    	final Color oldColor = g.getColor();
		g.setFont(font);
		int i = 9;
		for (final ActionChain action : standardActions) {
			g.setColor(action.check() ? ENABLED_COLOR : DISABLED_COLOR);
			renderText(g, action.getKey() + ": " + action.getDescription(), i--);
		}
		g.setColor(game.getActionHandler().canUndo() ? ENABLED_COLOR : DISABLED_COLOR);
		if (game.getActionHandler().canUndo()) renderText(g, "u: Undo", 2);
		g.setColor(game.getActionHandler().canRedo() ? ENABLED_COLOR : DISABLED_COLOR);
		if (game.getActionHandler().canRedo()) renderText(g, "r: Redo", 1);
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
