package tm.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.CardWithMarkers;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.completable.Completable;
import tm.completable.SelectCardsCompletable;

public class RoboticWorkforce extends CardWithMarkers {

    public RoboticWorkforce() {
        super("Robotic Workforce", 9, Tags.SCIENCE);
    }

    @Nullable
    @Override
    public Action getInitialAction(Game game) {
        final Map<Card, IncomeDeltaAction> actionMap = new HashMap<>();
        for (Card card : game.getCurrentPlayer().getPlayedCards()) {
            final IncomeDeltaAction action = ActionChain.findIncomeAction(card.getInitialAction(game));
            if (action != null) {
                actionMap.put(card, action);
            }
        }
        if (actionMap.isEmpty()) {
            return null;
        } else if (actionMap.size() == 1) {
            final Action action = actionMap.values().iterator().next();
            if (action.check(game)) {
                return action;
            }
            return null;
        } else {
            return new Action() {
                @Override
                public Completable begin(Game game) {
                    return new SelectCardsCompletable(game, new ArrayList<>(actionMap.keySet())) {
                        @Nullable
                        private Card selectedCard;

                        @Override
                        public boolean check() {
                            if (selectedCards.isEmpty()) {
                                System.err.println("You must select income to duplicate");
                                return false;
                            }
                            selectedCard = selectedCards.iterator().next();
                            final IncomeDeltaAction action = actionMap.get(selectedCard);
                            if (!action.check(game)) {
                                System.err.println("Cannot select this card");
                                return false;
                            }
                            return true;
                        }

                        @Override
                        public int maxSelection() {
                            return 1;
                        }

                        @Override
                        public String getTitle() {
                            return "Select income to duplicate";
                        }

                        @Override
                        public void complete() {
                            game.getActionHandler().addPendingAction(actionMap.get(selectedCard));
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
