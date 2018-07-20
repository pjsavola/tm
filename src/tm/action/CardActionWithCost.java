package tm.action;

import com.sun.istack.internal.Nullable;
import tm.Game;
import tm.Player;
import tm.Resources;
import tm.completable.Completable;

public abstract class CardActionWithCost extends CardAction {

    private final int cost;
    private final boolean titanium;
    @Nullable
    private PlayCardAction.Payment payment;

    public CardActionWithCost(boolean undoable, int cost, boolean titanium) {
        super(undoable);
        this.cost = cost;
        this.titanium = titanium;
    }

    @Override
    public boolean check(Game game) {
        if (!super.check(game)) {
            return false;
        }
        final Player player = game.getCurrentPlayer();
        final int totalTitaniumValue = titanium ? player.getTitanium() * player.getTitaniumValue() : 0;
        return player.canAdjustResources(new Resources(totalTitaniumValue - cost));
    }

    public void initPayment(Player player) {
        payment = new PlayCardAction.Payment(player, false, titanium, cost, 0);
    }

    public void resetPayment(Player player) {
        payment = null;
        player.setResourcesDelta(new Resources(0));
        player.setIncomeDelta(new Resources(0));
    }

    public boolean adjustPayment(boolean steel, boolean increment) {
        if (payment != null && payment.steel == steel && payment.titanium == !steel) {
            if (increment) {
                payment.increment();
            } else {
                payment.decrement();
            }
            return true;
        }
        return false;
    }

    public Resources getResources() {
        return payment.getResources();
    }

    @Override
    public Completable begin(Game game) {
        return new CardActionCompletable(game, this) {
            @Override
            public void complete() {
                game.getActionHandler().addPendingAction(new ResourceDeltaAction(getResources()));
                super.complete();
            }
        };
    }
}
