package tm.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.Game;
import tm.Player;
import tm.Resources;
import tm.Tags;
import tm.completable.Completable;
import tm.completable.SelectItemsCompletable;

public class PlayCardAction implements Action {

    private final Set<Card> hand;
    private final String title;
    private final int discount;
    private final int tolerance;

    public PlayCardAction(Set<Card> hand, String title) {
        this(hand, title, 0, 0);
    }

    public PlayCardAction(Set<Card> hand, String title, int discount, int tolerance) {
        this.hand = hand;
        this.title = title;
        this.discount = discount;
        this.tolerance = tolerance;
    }

    @Override
    public boolean check(Game game) {
        return !hand.isEmpty();
    }

    @Override
    public Completable begin(Game game) {
        return new PlayCardCompletable(game, hand, title, discount, tolerance);
    }

    private static class PlayCardCompletable extends SelectItemsCompletable<Card> {

        private final Game game;
        private Collection<Card> hand;
        private final Player player;
        @Nullable
        private Card selectedCard;
        @Nullable
        private Payment payment;
        private final int discount;
        private final int tolerance;

        public PlayCardCompletable(Game game, Set<Card> hand, String title, int discount, int tolerance) {
            super(game, new ArrayList<>(hand), 1, 1, title);
            player = game.getCurrentPlayer();
            this.game = game;
            this.hand = hand;
            this.discount = discount;
            this.tolerance = tolerance;
        }

        @Override
        public boolean check() {
            if (!player.fulfillsRequirements(selectedCard, game, tolerance)) {
                System.err.println("Card requirements not fulfilled");
                return false;
            }
            if (!game.getCurrentPlayer().canAdjustResources(payment.getResourceDelta())) {
                System.err.println("Not enough resources to play the card");
                return false;
            }
            if (!game.getCurrentPlayer().canAdjustIncome(payment.getIncomeDelta())) {
                System.err.println("Not enough income to play the card");
                return false;
            }
            return true;
        }

        @Override
        public void complete() {
            if (payment != null) {
                game.getCurrentPlayer().adjustResources(payment.getResourceDelta());
                game.getCurrentPlayer().adjustIncome(payment.getIncomeDelta());
            }
            final Action action = selectedCard.getInitialAction(game);
            if (action != null && action.check(game)) {
                game.getActionHandler().addPendingAction(action);
            }
            player.addTags(selectedCard.getTags());
            hand.remove(selectedCard);
            player.playCard(selectedCard);
            player.cardPlayEffects(game, selectedCard);
            player.setResourcesDelta(Resources.EMPTY);
            player.setIncomeDelta(Resources.EMPTY);
        }

        @Override
        public void undo() {
            if (payment != null) {
                game.getCurrentPlayer().adjustResources(payment.getResourceDelta().negate());
                game.getCurrentPlayer().adjustIncome(payment.getIncomeDelta().negate());
            }
            player.removeTags(selectedCard.getTags());
            hand.add(selectedCard);
            player.unplayCard(selectedCard);
        }

        @Override
        public void redo() {
            if (payment != null) {
                game.getCurrentPlayer().adjustResources(payment.getResourceDelta());
                game.getCurrentPlayer().adjustIncome(payment.getIncomeDelta());
            }
            player.addTags(selectedCard.getTags());
            hand.remove(selectedCard);
            player.playCard(selectedCard);
        }

        @Override
        public void cancel() {
            player.setResourcesDelta(Resources.EMPTY);
            player.setIncomeDelta(Resources.EMPTY);
            super.cancel();
            game.repaint();
        }

        @Override
        public boolean adjustPayment(boolean steel, boolean increment) {
            if (payment != null) {
                return payment.adjust(steel, increment);
            }
            return false;
        }

        @Override
        protected void selectionChanged() {
            if (!selectedItems.isEmpty()) {
                selectedCard = selectedItems.iterator().next();
                payment = new Payment(
                    player,
                    selectedCard.getTags().has(Tags.Type.BUILDING),
                    selectedCard.getTags().has(Tags.Type.SPACE),
                    selectedCard.getResourceDelta(game).combine(new Resources(-selectedCard.getCost())),
                    selectedCard.getIncomeDelta(game),
                    player.getDiscount(selectedCard, discount)
                );
            } else {
                player.setResourcesDelta(Resources.EMPTY);
                player.setIncomeDelta(Resources.EMPTY);
                payment = null;
                selectedCard = null;
            }
        }
    }

    public static class Payment {
        private final Player player;
        final boolean steel;
        final boolean titanium;
        private final Resources resourceDeltaAfterDiscounts;
        private final Resources incomeDelta;
        private final int materialValue;
        private final int materialMax;
        private int materialsUsed;
        private final int cost;

        public Payment(Player player, boolean steel, boolean titanium, Resources resourceDelta, Resources incomeDelta, int discount) {
            this.player = player;
            this.steel = steel;
            this.titanium = titanium;
            this.incomeDelta = incomeDelta;
            resourceDeltaAfterDiscounts = resourceDelta.combine(new Resources(discount));
            cost = Math.max(0, -resourceDeltaAfterDiscounts.getMoney());
            if (steel) {
                materialValue = player.getSteelValue();
                materialsUsed = Math.min(player.getSteel(), cost / materialValue);
                if (player.getSteel() > materialsUsed && cost % materialValue != 0) {
                    materialMax = materialsUsed + 1;
                } else {
                    materialMax = materialsUsed;
                }
            } else if (titanium) {
                materialValue = player.getTitaniumValue();
                materialsUsed = Math.min(player.getTitanium(), cost / materialValue);
                if (player.getTitanium() > materialsUsed && cost % materialValue != 0) {
                    materialMax = materialsUsed + 1;
                } else {
                    materialMax = materialsUsed;
                }
            } else {
                materialValue = 0;
                materialMax = 0;
            }
            updateDelta();
        }

        public boolean adjust(boolean steel, boolean increment) {
            if (this.steel == steel && titanium == !steel) {
                if (increment) {
                    materialsUsed = Math.min(materialsUsed + 1, materialMax);
                } else {
                    materialsUsed = Math.max(0, materialsUsed - 1);
                }
                updateDelta();
                return true;
            }
            return false;
        }

        public Resources getResourceDelta() {
            if (steel) {
                return resourceDeltaAfterDiscounts.combine(new Resources(Math.min(cost, materialsUsed * materialValue), -materialsUsed, 0, 0, 0, 0));
            } else if (titanium) {
                return resourceDeltaAfterDiscounts.combine(new Resources(Math.min(cost, materialsUsed * materialValue), 0, -materialsUsed, 0, 0, 0));
            } else {
                return resourceDeltaAfterDiscounts;
            }
        }

        public Resources getIncomeDelta() {
            return incomeDelta;
        }

        private void updateDelta() {
            player.setResourcesDelta(getResourceDelta());
            player.setIncomeDelta(incomeDelta);
        }
    }
}
