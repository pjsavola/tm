package tm.corporation;

import java.awt.Graphics;
import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.CardAction;
import tm.action.CardActionWithCost;

public class Helion extends Corporation {

    private final CardAction action = new CardActionWithCost(true, ActionType.HEAT_TO_MONEY, new Resources(1, 0, 0, 0, 0, -1));

    public Helion() {
        super("Helion", Tags.SPACE, true);
    }

    @Override
    public Resources getResourceDelta(Game game) {
        return new Resources(42);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(0, 0, 0, 0, 0, 3);
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        g.drawString("May convert", x, y + 12);
        g.drawString("heat to money", x, y + 28);
    }
}
