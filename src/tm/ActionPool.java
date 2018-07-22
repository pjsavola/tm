package tm;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.sun.istack.internal.Nullable;
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
import tm.action.SelectActionAction;
import tm.action.SwitchRoundAction;
import tm.completable.Completable;

public class ActionPool {
    private static final Font font = new Font("Arial", Font.BOLD, 12);
    private final Game game;
    private final List<ActionChain> standardActions = new ArrayList<>();
    private static final Color ENABLED_COLOR = new Color(0xFFFFFF);
    private static final Color DISABLED_COLOR = new Color(0x666666);

    public ActionPool(Game game) {
        this.game = game;
        standardActions.add(new ActionChain(ActionType.DISCARD, "Discard",
            new DiscardAction()));
        standardActions.add(new ActionChain(ActionType.ENERGY, "Energy income",
            new ResourceDeltaAction(new Resources(-11)),
            new IncomeDeltaAction(new Resources(0, 0, 0, 0, 1, 0))));
        standardActions.add(new ActionChain(ActionType.TEMPERATURE, "Temperature",
            new ResourceDeltaAction(new Resources(-14)),
            new AddTemperatureAction()));
        standardActions.add(new ActionChain(ActionType.WATER, "Water",
            new ResourceDeltaAction(new Resources(-18)),
            new AddWaterAction()));
        standardActions.add(new ActionChain(ActionType.GREENERY, "Greenery",
            new ResourceDeltaAction(new Resources(-23)),
            new PlaceTileAction(Tile.Type.GREENERY),
            new AddOxygenAction()));
        standardActions.add(new ActionChain(ActionType.CITY, "City",
            new ResourceDeltaAction(new Resources(-25)),
            new PlaceTileAction(Tile.Type.CITY),
            new IncomeDeltaAction(new Resources(1))));
        standardActions.add(new ActionChain(ActionType.PLANT_TO_GREENERY, "Plant",
            new ResourceDeltaAction(new Resources(0, 0, 0, -8, 0, 0)),
            new PlaceTileAction(Tile.Type.GREENERY),
            new AddOxygenAction()));
        standardActions.add(new ActionChain(ActionType.HEAT_TO_TEMPERATURE, "Heat",
            new ResourceDeltaAction(new Resources(0, 0, 0, 0, 0, -8)),
            new AddTemperatureAction()));
        standardActions.add(new ActionChain(ActionType.PASS, "Pass",
            new SwitchRoundAction()));
        standardActions.add(new ActionChain(ActionType.PLAY, "Play card",
            new PlayCardAction()));
        standardActions.add(new ActionChain(ActionType.CUSTOM, "Select action",
            new SelectActionAction()));
    }

    @Nullable
    public Completable getCompletable(@Nullable ActionType type) {
        for (Action action : game.getCurrentPlayer().getActions()) {
            if (action.getType() == type && action.check(game)) {
                return action.begin(game);
            }
        }
        for (Action action : standardActions) {
            if (action.getType() == type && action.check(game)) {
                return action.begin(game);
            }
        }
        return null;
    }

    public void render(Graphics g) {
        final Color oldColor = g.getColor();
        final boolean canAct = game.getActionHandler().canAct();
        g.setFont(font);
        int i = 13;
        for (final ActionChain action : standardActions) {
            //g.setColor(canAct && action.check(game) ? ENABLED_COLOR : DISABLED_COLOR);
            //renderText(g, action.getKey() + ": " + action.getDescription(), i--);
        }
        g.setColor(game.getActionHandler().canUndo() ? ENABLED_COLOR : DISABLED_COLOR);
        renderText(g, "u: Undo", 2);
        g.setColor(game.getActionHandler().canRedo() ? ENABLED_COLOR : DISABLED_COLOR);
        renderText(g, "r: Redo", 1);
        g.setColor(oldColor);
    }

    private static void renderText(Graphics g, String text, int i) {
        final FontMetrics metrics = g.getFontMetrics();
        int w = metrics.stringWidth(text);
        int h = metrics.getHeight();
        g.drawString(text, 698 - w, 698 - h * i);
    }
}
