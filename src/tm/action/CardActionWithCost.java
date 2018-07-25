package tm.action;

import com.sun.istack.internal.Nullable;
import tm.Game;
import tm.Player;
import tm.Resources;
import tm.completable.Completable;

public abstract class CardActionWithCost extends CardAction {

    private final Resources resourceDelta;
    private final Resources incomeDelta;
    private final boolean titanium;
    @Nullable
    private PlayCardAction.Payment payment;

    public CardActionWithCost(boolean undoable, int cost) {
        this(undoable, new Resources(-cost));
    }

    public CardActionWithCost(boolean undoable, Resources resourceDelta) {
        this(undoable, resourceDelta, Resources.EMPTY, false);
    }

    public CardActionWithCost(boolean undoable, Resources resourceDelta, Resources incomeDelta) {
        this(undoable, resourceDelta, incomeDelta, false);
    }

    public CardActionWithCost(boolean undoable, Resources resourceDelta, Resources incomeDelta, boolean titainum) {
        super(undoable);
        this.resourceDelta = resourceDelta;
        this.incomeDelta = incomeDelta;
        this.titanium = titainum;
    }

    @Override
    public boolean check(Game game) {
        if (!super.check(game)) {
            return false;
        }
        final Player player = game.getCurrentPlayer();
        final int totalTitaniumValue = titanium ? player.getTitanium() * player.getTitaniumValue() : 0;
        if (!player.canAdjustResources(resourceDelta.combine(new Resources(totalTitaniumValue)))) {
            return false;
        }
        return player.canAdjustIncome(incomeDelta);
    }

    public void initPayment(Player player) {
        payment = new PlayCardAction.Payment(player, false, titanium, resourceDelta, incomeDelta, 0);
    }

    public void resetPayment(Player player) {
        payment = null;
        player.setResourcesDelta(Resources.EMPTY);
        player.setIncomeDelta(Resources.EMPTY);
    }

    public boolean adjustPayment(boolean steel, boolean increment) {
        if (payment != null) {
            payment.adjust(steel, increment);
        }
        return false;
    }

    @Override
    public Completable begin(Game game) {
        return new CardActionCompletable(game, this) {
            @Override
            public void complete() {
                game.getActionHandler().addPendingAction(new ActionChain(
                    new ResourceDeltaAction(payment.getResourceDelta()),
                    new IncomeDeltaAction(payment.getIncomeDelta())
                ));
                super.complete();
            }
        };
    }
}
