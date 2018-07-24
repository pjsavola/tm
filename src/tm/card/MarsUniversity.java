package tm.card;

import java.util.Arrays;
import java.util.List;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.Game;
import tm.Player;
import tm.Tags;
import tm.action.Action;
import tm.action.DrawCardsAction;
import tm.completable.Completable;
import tm.completable.SelectCardsCompletable;

public class MarsUniversity extends Card {

    public MarsUniversity() {
        super("Mars University", 8, Tags.SCIENCE.combine(Tags.BUILDING), true);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Effect:", "When playing card with science tag", "(including this)", "you may discard a card to draw a card");
    }

    public static class Effect implements Action {
        private final Player player;

        public Effect(Player player) {
            this.player = player;
        }

        @Override
        public Completable begin(Game game) {
            return new SelectCardsCompletable(game, player.getCards()) {
                @Nullable
                private Card selectedCard;

                @Override
                public boolean check() {
                    return true;
                }

                @Override
                public int maxSelection() {
                    return 1;
                }

                @Override
                public String getTitle() {
                    return "You may discard 1 card to draw 1 card";
                }

                @Override
                public void complete() {
                    selectedCard = selectedCards.isEmpty() ? null : selectedCards.iterator().next();
                    if (selectedCard != null) {
                        game.getCurrentPlayer().getCards().remove(selectedCard);
                        game.getActionHandler().addPendingIrreversibleAction(new DrawCardsAction(1, false, false));
                    }
                    cancel();
                }

                @Override
                public void undo() {
                    // Can only be done if card wasn't selected, so nothing happens
                }

                @Override
                public void redo() {
                }
            };
        }
    }
}
