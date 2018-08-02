package tm.card;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.CardAction;
import tm.action.CardActionWithCost;
import tm.action.ResourceDeltaAction;

public class MartianRail extends Card {

    private final CardAction action = new CardActionWithCost(true, ActionType.MARTIAN_RAIL, Resources.ENERGY.negate()) {
        @Override
        protected Action getAction(Game game) {
            return new ResourceDeltaAction(null) {
                @Override
                public Resources getDelta(Game game) {
                    return new Resources(game.getCityCount(true));
                }
                @Override
                public void render(Graphics g, int x, int y, Game game) {
                    g.setColor(Color.LIGHT_GRAY);
                    Resources.MONEY.render(g, x, y, false);
                    g.drawString("/ city on Mars", x + 24, y + 12);
                }
            };
        }
    };

    public MartianRail() {
        super("Martian Rail", 13, Tags.BUILDING);
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "1 money for each city", "-1 energy");
    }
}
