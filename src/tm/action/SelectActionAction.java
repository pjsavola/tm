package tm.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.Game;
import tm.Resources;
import tm.completable.Completable;
import tm.completable.SelectItemsCompletable;

public class SelectActionAction implements Action {

    @Override
    public boolean check(Game game) {
        return game.getCurrentPlayer().getPlayedCards().stream().filter(card -> !card.getActions().isEmpty()).count() > 0;
    }

    @Override
    public Completable begin(Game game) {
        final List<CardAction> selectableActions = new ArrayList<>();
        final Map<CardAction, Card> cardMap = new HashMap<>();
        game.getCurrentPlayer().getPlayedCards().forEach(card -> {
            selectableActions.addAll(card.getActions());
            card.getActions().forEach(action -> cardMap.put(action, card));
        });
        return new SelectActionCompletable<>(game, selectableActions, cardMap);
    }

    public static class SelectActionCompletable<T extends CardAction> extends SelectItemsCompletable<T> {
        private final Game game;
        @Nullable
        private final Map<T, Card> cardMap;
        private T selectedAction;

        public SelectActionCompletable(Game game, List<T> selectableActions) {
            this(game, selectableActions, null);
        }

        public SelectActionCompletable(Game game, List<T> selectableActions, @Nullable Map<T, Card> cardMap) {
            super(game, selectableActions, 1, 1, "Select action");
            this.game = game;
            this.cardMap = cardMap;
        }

        @Override
        public boolean check() {
            if (selectedAction != null) {
                if (!selectedAction.check(game)) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public void complete() {
            if (selectedAction != null) {
                game.getActionHandler().addPendingAction(selectedAction);
            }
            cancel();
        }

        @Override
        public void undo() {
        }

        @Override
        public void redo() {
        }

        @Override
        public void cancel() {
            game.getCurrentPlayer().setResourcesDelta(Resources.EMPTY);
            game.getCurrentPlayer().setIncomeDelta(Resources.EMPTY);
            super.cancel();
            game.repaint();
        }

        @Override
        public boolean adjustPayment(boolean steel, boolean increment) {
            if (selectedAction instanceof CardActionWithCost) {
                return ((CardActionWithCost) selectedAction).adjustPayment(steel, increment);
            }
            return false;
        }

        @Override
        protected void selectionChanged() {
            if (selectedAction instanceof CardActionWithCost) {
                ((CardActionWithCost) selectedAction).resetPayment(game.getCurrentPlayer());
            }
            if (selectedItems.isEmpty()) {
                selectedAction = null;
            } else {
                selectedAction = selectedItems.iterator().next();
                if (selectedAction instanceof CardActionWithCost) {
                    ((CardActionWithCost) selectedAction).initPayment(game.getCurrentPlayer());
                }
            }
        }

        @Override
        protected Card getCard(T action) {
            return cardMap.get(action);
        }
    }
}
