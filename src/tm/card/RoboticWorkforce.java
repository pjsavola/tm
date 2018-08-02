package tm.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.Game;
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
        final Map<Card, Resources> incomeMap = new HashMap<>();
        for (Card card : game.getCurrentPlayer().getPlayedCards()) {
            final Resources income = card.getIncomeDelta();
            if (income != Resources.EMPTY) {
                if (game.getCurrentPlayer().canAdjustIncome(income)) {
                    incomeMap.put(card, income);
                }
            }
        }
        if (incomeMap.isEmpty()) {
            return null;
        } else if (incomeMap.size() == 1) {
            return new IncomeDeltaAction(incomeMap.values().iterator().next());
        } else {
            return new Action() {
                @Override
                public Completable begin(Game game) {
                    return new SelectItemsCompletable<Card>(game, new ArrayList<>(incomeMap.keySet()), 1, 1, "Select income to duplicate") {
                        @Nullable
                        private Card selectedCard;

                        @Override
                        public void complete() {
                            game.getActionHandler().addPendingAction(new IncomeDeltaAction(incomeMap.get(selectedCard)));
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
            };
        }
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("Duplicate income part of any played card");
    }
}
