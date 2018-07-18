package tm.action;

import java.util.ArrayList;
import java.util.List;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.Game;
import tm.Player;
import tm.Resources;
import tm.Tags;
import tm.completable.Completable;
import tm.completable.SelectCardsCompletable;
import tm.corporation.Credicor;
import tm.corporation.InterplanetaryCinematics;
import tm.corporation.SaturnSystems;

public class PlayCardAction implements Action {

    final Player player;

    public PlayCardAction(Player player) {
        this.player = player;
    }

    @Override
    public char getKey() {
        return 'x';
    }

    @Override
    public boolean check(Game game) {
        return !player.getCards().isEmpty();
    }

    @Override
    public Completable begin(Game game) {
        final List<Card> hand = new ArrayList<>(player.getCards());
        return new PlayCardCompletable(game, hand);
    }

    private class PlayCardCompletable extends SelectCardsCompletable {

        private final Game game;
        private final List<Card> hand;
        @Nullable
        private Payment payment;

        public PlayCardCompletable(Game game, List<Card> hand) {
            super(game, player.getCards());
            this.game = game;
            this.hand = hand;
        }

        @Override
        public int maxSelection() {
            return 1;
        }

        @Override
        public boolean check() {
            if (selectedCards.isEmpty()) {
                System.err.println("You must select a card to play it");
                return false;
            }
            final Card card = selectedCards.iterator().next();
            if (!player.fulfillsRequirements(card, game.getPlanet())) {
                System.err.println("Card requirements not fulfilled");
                return false;
            }
            final Action paymentAction = new ResourceDeltaAction(payment.getResources());
            if (paymentAction.check(game)) {
                game.getActionHandler().addPendingAction(paymentAction);
                if (card.getCost() >= 20 && player.getCorporation() instanceof Credicor) {
                    game.getActionHandler().addPendingAction(new ResourceDeltaAction(new Resources(4)));
                }
                if (card.getTags().hasEvent() && player.getCorporation() instanceof InterplanetaryCinematics) {
                    game.getActionHandler().addPendingAction(new ResourceDeltaAction(new Resources(2)));
                }
                if (card.getTags().hasJovian()) {
                    final Player saturnSystemPlayer = game.getPlayer(SaturnSystems.class);
                    if (saturnSystemPlayer != null) {
                        game.getActionHandler().addPendingAction(new IncomeDeltaAction(new Resources(1), saturnSystemPlayer));
                    }
                }
                return true;
            } else {
                System.err.println("Not enough money to pay for the card");
                return false;
            }
        }

        @Override
        public void complete() {
            player.getCards().removeAll(selectedCards);
            cancel();
        }

        @Override
        public void undo() {
            player.getCards().clear();
            player.getCards().addAll(hand);
            game.repaint();
        }

        @Override
        public void redo() {
            player.getCards().removeAll(selectedCards);
            game.repaint();
        }

        @Override
        public void cancel() {
            player.setResourcesDelta(new Resources(0));
            player.setIncomeDelta(new Resources(0));
            payment = null;
            super.cancel();
        }

        @Override
        public String getTitle() {
            return "Select card to play";
        }

        @Override
        public boolean pressKey(char key) {
            if (payment != null) {
                if (key == '-') {
                    payment.decrement();
                    return true;
                } else if (key == '+') {
                    payment.increment();
                    return true;
                }
            }
            return false;
        }

        @Override
        protected void selectionChanged() {
            if (!selectedCards.isEmpty()) {
                final Card card = selectedCards.iterator().next();
                final Tags tags = card.getTags();
                payment = new Payment(card);
            } else {
                player.setResourcesDelta(new Resources(0));
                player.setIncomeDelta(new Resources(0));
                payment = null;
            }
        }

        class Payment {
            private final boolean steel;
            private final boolean titanium;
            private final int costAfterDiscounts;
            private final int materialValue;
            private final int materialMax;
            private int materialsUsed;

            private Payment(Card card) {
                steel = card.getTags().hasBuilding();
                titanium = card.getTags().hasSpace();
                costAfterDiscounts = card.getCost() - player.getDiscount(card);
                if (steel) {
                    materialValue = player.getSteelValue();
                    materialsUsed = Math.min(player.getSteel(), costAfterDiscounts / materialValue);
                    if (player.getSteel() > materialsUsed && card.getCost() % materialValue != 0) {
                        materialMax = materialsUsed + 1;
                    } else {
                        materialMax = materialsUsed;
                    }
                } else if (titanium) {
                    materialValue = player.getTitaniumValue();
                    materialsUsed = Math.min(player.getTitanium(), costAfterDiscounts / materialValue);
                    if (player.getTitanium() > materialsUsed && card.getCost() % materialValue != 0) {
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

            private void increment() {
                materialsUsed = Math.min(materialsUsed + 1, materialMax);
                updateDelta();
            }

            private void decrement() {
                materialsUsed = Math.max(0, materialsUsed - 1);
                updateDelta();
            }

            private Resources getResources() {
                if (steel) {
                    return new Resources(materialsUsed * materialValue - costAfterDiscounts, -materialsUsed, 0, 0, 0, 0);
                } else if (titanium) {
                    return new Resources(materialsUsed * materialValue - costAfterDiscounts, 0, -materialsUsed, 0, 0, 0);
                } else {
                    return new Resources(-costAfterDiscounts);
                }
            }

            private void updateDelta() {
                player.setResourcesDelta(getResources());
            }
        }
    }
}
