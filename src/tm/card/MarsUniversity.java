package tm.card;

import java.awt.Graphics;
import java.util.ArrayList;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.Game;
import tm.ImageCache;
import tm.Player;
import tm.Tags;
import tm.action.Action;
import tm.action.DrawCardsAction;
import tm.completable.Completable;
import tm.completable.SelectItemsCompletable;
import tm.effect.PlayCardEffect;

public class MarsUniversity extends Card implements PlayCardEffect {

    public MarsUniversity() {
        super("Mars University", 8, Tags.SCIENCE_BUILDING, null, true);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        g.drawImage(ImageCache.getImage("images/tag_science.png"), x, y + 8, null);
        g.drawString("discard 1", x + 24, y + 12);
        g.drawString("to draw 1", x + 24, y + 28);
    }

    @Nullable
    @Override
    public Action cardPlayed(Card card) {
        final int count = card.getTags().getCount(Tags.Type.SCIENCE);
        if (count == 0) {
            return null;
        }
        return new DiscardToDrawAction(count);
    }

    private static class DiscardToDrawAction implements Action {

        private final int iterations;

        private DiscardToDrawAction(int iterations) {
            this.iterations = iterations;
        }

        @Override
        public Completable begin(Game game) {
            final Player player = game.getCurrentPlayer();
            return new SelectItemsCompletable<Card>(game, new ArrayList<>(player.getCards()), 0, 1, "You may discard 1 card to draw 1 card") {
                @Nullable
                private Card selectedCard;

                @Override
                public void complete() {
                    selectedCard = selectedItems.isEmpty() ? null : selectedItems.iterator().next();
                    if (selectedCard != null) {
                        game.getCurrentPlayer().getCards().remove(selectedCard);
                        game.getActionHandler().addPendingAction(new DrawCardsAction(1, false, false));
                        if (iterations > 1) {
                            game.getActionHandler().addPendingAction(new DiscardToDrawAction(iterations - 1));
                        }
                    }
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
