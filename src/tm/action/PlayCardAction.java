package tm.action;

import java.util.ArrayList;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.Game;
import tm.Player;
import tm.Resources;
import tm.Tags;
import tm.completable.Completable;
import tm.completable.SelectCardsCompletable;

public class PlayCardAction implements Action {

    private final int discount;
    private final int tolerance;

    public PlayCardAction() {
        this(0, 0);
    }

    public PlayCardAction(int discount, int tolerance) {
        this.discount = discount;
        this.tolerance = tolerance;
    }

    @Override
    public boolean check(Game game) {
        return !game.getCurrentPlayer().getCards().isEmpty();
    }

    @Override
    public Completable begin(Game game) {
        return new PlayCardCompletable(game, discount, tolerance);
    }

    private static class PlayCardCompletable extends SelectCardsCompletable {

        private final Game game;
        private final Player player;
        @Nullable
        private Card selectedCard;
        @Nullable
        private Payment payment;
        private final int discount;
        private final int tolerance;

        public PlayCardCompletable(Game game, int discount, int tolerance) {
            super(game, new ArrayList<>(game.getCurrentPlayer().getCards()));
            player = game.getCurrentPlayer();
            this.game = game;
            this.discount = discount;
            this.tolerance = tolerance;
        }

        @Override
        public int maxSelection() {
            return 1;
        }

        @Override
        public boolean check() {
            if (selectedCard == null) {
                System.err.println("You must select a card to play it");
                return false;
            }
            if (!player.fulfillsRequirements(selectedCard, game, tolerance)) {
                System.err.println("Card requirements not fulfilled");
                return false;
            }
            final Action paymentAction = new ResourceDeltaAction(payment.getResourceDelta());
            if (!paymentAction.check(game)) {
                System.err.println("Not enough money to pay for the card");
                return false;
            }
            final Action action = selectedCard.getInitialAction(game);
            if (action != null && !action.check(game)) {
                System.err.println("Not enough resources to play the card");
                return false;
            }
            game.getActionHandler().addPendingAction(paymentAction);
            if (action != null) {
                game.getActionHandler().addPendingAction(action);
            }
            return true;
        }

        @Override
        public void complete() {
            player.addTags(selectedCard.getTags());
            player.getCards().remove(selectedCard);
            player.playCard(selectedCard);
            player.cardPlayEffects(game, selectedCard);
            cancel();
        }

        @Override
        public void undo() {
            player.removeTags(selectedCard.getTags());
            player.getCards().add(selectedCard);
            player.unplayCard(selectedCard);
            game.repaint();
        }

        @Override
        public void redo() {
            player.addTags(selectedCard.getTags());
            player.getCards().remove(selectedCard);
            player.playCard(selectedCard);
            game.repaint();
        }

        @Override
        public void cancel() {
            player.setResourcesDelta(Resources.EMPTY);
            player.setIncomeDelta(Resources.EMPTY);
            payment = null;
            super.cancel();
        }

        @Override
        public String getTitle() {
            return "Select card to play";
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
            if (!selectedCards.isEmpty()) {
                selectedCard = selectedCards.iterator().next();
                payment = new Payment(
                    player,
                    selectedCard.getTags().has(Tags.Type.BUILDING),
                    selectedCard.getTags().has(Tags.Type.SPACE),
                    new Resources(-selectedCard.getCost()),
                    Resources.EMPTY,
                    player.getDiscount(selectedCard, discount)
                );
            } else {
                player.setResourcesDelta(new Resources(0));
                player.setIncomeDelta(new Resources(0));
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

        public Payment(Player player, boolean steel, boolean titanium, Resources resourceDelta, Resources incomeDelta, int discount) {
            this.player = player;
            this.steel = steel;
            this.titanium = titanium;
            this.incomeDelta = incomeDelta;
            resourceDeltaAfterDiscounts = resourceDelta.combine(new Resources(discount));
            final int cost = -resourceDeltaAfterDiscounts.getMoney();
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
                return resourceDeltaAfterDiscounts.combine(new Resources(materialsUsed * materialValue, -materialsUsed, 0, 0, 0, 0));
            } else if (titanium) {
                return resourceDeltaAfterDiscounts.combine(new Resources(materialsUsed * materialValue, 0, -materialsUsed, 0, 0, 0));
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
