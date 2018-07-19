package tm;

import java.awt.Color;
import java.awt.Font;

import tm.action.Action;

public abstract class Corporation extends Card {

    public static final int WIDTH = 200;
    public static final int TITLE_HEIGHT = 16;
    private static final Color TEXT_COLOR = new Color(0xFFFFFF);
    private static final Color BG_COLOR = new Color(0x000000);
    private static final Font FONT = new Font("Arial", Font.BOLD, 12);

    protected Corporation(String name, Tags tags) {
        super(name, 0, tags, false);
    }

    @Override
    public Color getBorderColor() {
        return new Color(0x999999);
    }

    @Override
    public int getCost() {
        throw new UnsupportedOperationException();
    }

    public boolean start(Game game) {
        final Action action = getInitialAction();
        if (action != null) {
            if (action.check(game)) {
                game.getActionHandler().addPendingAction(action);
                return true;
            }
        }
        return false;
    }
}
