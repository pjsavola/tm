package tm.card;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;
import tm.Card;
import tm.Game;
import tm.Renderer;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;
import tm.completable.Completable;
import tm.completable.SelectItemsCompletable;

public class RoboticWorkforce extends Card {

    public RoboticWorkforce() {
        super("Robotic Workforce", 9, Tags.SCIENCE);
    }

    @Nullable
    @Override
    public Action getInitialAction(Game game) {
        return new Action() {
            @Override
            public Completable begin(Game game) {
                final Map<Card, IncomeDeltaAction> incomeMap = new HashMap<>();
                for (Card card : game.getCurrentPlayer().getPlayedCards()) {
                    if (!card.getTags().has(Tags.Type.BUILDING)) {
                        continue;
                    }
                    final Resources income = card.getIncomeDelta();
                    if (income != Resources.EMPTY) {
                        if (game.getCurrentPlayer().canAdjustIncome(income)) {
                            incomeMap.put(card, new IncomeDeltaAction(income));
                        }
                    } else {
                        final Action action = card.getInitialAction(game);
                        if (action instanceof IncomeDeltaAction) {
                            if (action.check(game)) {
                                incomeMap.put(card, (IncomeDeltaAction) action);
                            }
                        }
                    }
                }
                return new SelectItemsCompletable<Card>(game, new ArrayList<>(incomeMap.keySet()), 1, 1, "Select income to duplicate") {
                    @Override
                    public void complete() {
                        final Card card = selectedItems.iterator().next();
                        game.getActionHandler().addPendingAction(incomeMap.get(card));
                        cancel();
                    }

                    @Override
                    public void undo() {
                    }

                    @Override
                    public void redo() {
                    }
                };
            }

            @Override
            public Point render(Graphics g, int x, int y, Game game) {
                g.setColor(Color.WHITE);
                final Point p = Renderer.renderText(g, "Duplicate income of", x, y + 3, false);
                return Tags.BUILDING.render(g, p.x + 4, y, true);
            }
        };
    }
}
