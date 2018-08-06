package tm.card;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tm.Card;
import tm.Game;
import tm.Renderer;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.CardAction;
import tm.action.IncomeDeltaAction;
import tm.action.SelectActionAction;
import tm.completable.Completable;

public class Insulation extends Card {

    public Insulation() {
        super("Insulation", 2, Tags.SCIENCE);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new Action() {
            @Override
            public Completable begin(Game game) {
                final List<CardAction> actions = new ArrayList<>();
                final int max = game.getCurrentPlayer().getIncome().getHeat();
                for (int i = 1; i <= max; i++) {
                    final int x = i;
                    actions.add(new CardAction(true, null) {
                        @Override
                        public String getDescription() {
                            return "" + x + "money income, -" + x + " heat income";
                        }
                        @Override
                        public Action getAction(Game game) {
                            return new IncomeDeltaAction(new Resources(x, 0, 0, 0, 0, -x));
                        }
                    });
                }
                final Map<CardAction, Card> cardMap = new HashMap<>();
                actions.forEach(cardAction -> cardMap.put(cardAction, Insulation.this));
                return new SelectActionAction.SelectActionCompletable<>(game, actions, cardMap);
            }

            @Override
            public Point render(Graphics g, int x, int y, Game game) {
                final Point p = Resources.EMPTY.renderHeat(g, x, y, true, false);
                g.setColor(Color.WHITE);
                Renderer.renderText(g, "-X", p.x + 4, y + 4, false);
                final Point p2 = Resources.EMPTY.renderMoney(g, x, p.y + 4, true, false);
                g.setColor(Color.WHITE);
                return Renderer.renderText(g, "X", p2.x + 4, p.y + 8, false);
            }
        };
    }
}
