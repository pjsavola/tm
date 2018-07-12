package tm.action;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Player;
import tm.Resources;
import tm.Tags;
import tm.completable.Completable;
import tm.completable.SelectCardsCompletable;
import tm.corporation.Credicor;

public class PlayCardAction implements Action {

    @Override
    public char getKey() {
        return 'x';
    }

    @Override
    public boolean check(Game game) {
        return !game.getCurrentPlayer().getCards().isEmpty();
    }

    @Override
    public Completable begin(Game game) {
        final List<Card> hand = new ArrayList<>(game.getCurrentPlayer().getCards());
        return new PlayCardCompletable(game, hand);
    }

    private static class PlayCardCompletable extends SelectCardsCompletable {

        private final Game game;
        private final List<Card> hand;
        private int materialsUsed;
        private int materialMax;
        private int materialValue;

        public PlayCardCompletable(Game game, List<Card> hand) {
            super(game, game.getCurrentPlayer().getCards());
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
                System.err.println ("You must select a card to play it");
                return false;
            }
            final Card card = selectedCards.iterator().next();
            final int cost = Math.max(0, card.getCost() - materialsUsed * materialValue);
            final Resources payment;
            if (card.getTags().hasBuilding()) {
                payment = new Resources(-cost, -materialsUsed, 0, 0, 0, 0);
            } else {
                payment = new Resources(-cost, 0, -materialsUsed, 0, 0, 0);
            }
            final Action paymentAction = new ResourceDeltaAction(payment);
            if (paymentAction.check(game)) {
                game.getActionHandler().addPendingAction(paymentAction);
                if (card.getCost() >= 20 && game.getCurrentPlayer().getCorporation() instanceof Credicor) {
                    game.getActionHandler().addPendingAction(new ResourceDeltaAction(new Resources(4)));
                }
                return true;
            } else {
                System.err.println("Not enough money to pay for the card");
                return false;
            }
        }

        @Override
        public void complete() {
            game.getCurrentPlayer().getCards().removeAll(selectedCards);
            cancel();
        }

        @Override
        public void undo() {
            game.getCurrentPlayer().getCards().clear();
            game.getCurrentPlayer().getCards().addAll(hand);
            game.repaint();
        }

        @Override
        public void redo() {
            game.getCurrentPlayer().getCards().removeAll(selectedCards);
            game.repaint();
        }

        @Override
        public String getTitle() {
            return "Select card to play";
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            if (!selectedCards.isEmpty()) {
                final Color oldColor = g.getColor();
                g.setColor(Color.BLUE);
                final int x = LEFT_MARGIN + Card.WIDTH + VISIBLE_SPACING;
                final int y = TOP_MARGIN + CARD_HEIGHT + 40;
                g.drawString("Materials used: " + materialsUsed, x, y);
                g.setColor(oldColor);
            }
        }

        @Override
        public boolean pressKey(char key) {
            if (key == '-') {
                materialsUsed = Math.max(0, materialsUsed - 1);
                return true;
            } else if (key == '+') {
                materialsUsed = Math.min(materialsUsed + 1, materialMax);
                return true;
            }
            return false;
        }

        @Override
        protected void selectionChanged() {
            if (!selectedCards.isEmpty()) {
                final Card card = selectedCards.iterator().next();
                final Tags tags = card.getTags();
                final Player player = game.getCurrentPlayer();
                if (tags.hasBuilding()) {
                    // TODO: Steel value
                    materialValue = 2;
                    materialsUsed = Math.min(player.getSteel(), card.getCost() / materialValue);
                    materialMax = materialsUsed;
                    if (player.getSteel() > materialsUsed && card.getCost() % materialValue != 0) {
                        materialMax++;
                    }
                } else if (tags.hasSpace()) {
                    // TODO: Titanium value
                    materialValue = 3;
                    materialsUsed = Math.min(player.getTitanium(), card.getCost() / materialValue);
                    materialMax = materialsUsed;
                    if (player.getTitanium() > materialsUsed && card.getCost() % materialValue != 0) {
                        materialMax++;
                    }
                }
            }
        }
    }
}
