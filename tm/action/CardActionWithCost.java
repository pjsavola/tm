package tm.action;

import javax.annotation.Nullable;
import tm.ActionType;
import tm.Game;
import tm.Player;
import tm.Resources;
import tm.completable.Completable;

public class CardActionWithCost extends CardAction {

    private final Resources resourceDelta;
    private final Resources incomeDelta;
    private final boolean steel;
    private final boolean titanium;
    @Nullable
    private PlayCardAction.Payment payment;

    public CardActionWithCost(boolean undoable, ActionType type, Resources resourceDelta) {
        this(undoable, type, resourceDelta, Resources.EMPTY, false, false);
    }

    public CardActionWithCost(boolean undoable, ActionType type, Resources resourceDelta, Resources incomeDelta) {
        this(undoable, type, resourceDelta, incomeDelta, false, false);
    }

    public CardActionWithCost(boolean undoable, ActionType type, Resources resourceDelta, Resources incomeDelta, boolean steel, boolean titainum) {
        super(undoable, type);
        this.resourceDelta = resourceDelta;
        this.incomeDelta = incomeDelta;
        this.steel = steel;
        this.titanium = titainum;
    }

    @Override
    public Resources getResourceDelta(Game game) {
        return resourceDelta;
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return incomeDelta;
    }

    @Nullable
    @Override
    protected Action getAction(Game game) {
        return null;
    }

    @Override
    public boolean check(Game game) {
        if (!super.check(game)) {
            return false;
        }
        final Player player = game.getCurrentPlayer();
        final int resourceValue;
        if (steel) {
            resourceValue = player.getSteel() * player.getSteelValue();
        } else if (titanium) {
            resourceValue = player.getTitanium() * player.getTitaniumValue();
        } else {
            resourceValue = 0;
        }
        if (!player.canAdjustResources(resourceDelta.combine(new Resources(resourceValue)))) {
            return false;
        }
        return player.canAdjustIncome(incomeDelta);
    }

    public void initPayment(Player player) {
        payment = new PlayCardAction.Payment(player, steel, titanium, resourceDelta, incomeDelta, 0);
    }

    public void resetPayment(Player player) {
        payment = null;
        player.setResourcesDelta(Resources.EMPTY);
        player.setIncomeDelta(Resources.EMPTY);
    }

    public boolean adjustPayment(boolean steel, boolean increment) {
        if (payment != null) {
            return payment.adjust(steel, increment);
        }
        return false;
    }

    @Override
    public Completable begin(Game game) {
        return new CardActionCompletable(game, this) {
            @Override
            public void complete() {
                game.getCurrentPlayer().adjustResources(payment.getResourceDelta());
                game.getCurrentPlayer().adjustIncome(payment.getIncomeDelta());
                super.complete();
            }

            @Override
            public void undo() {
                if (isUndoable()) {
                    super.undo();
                    game.getCurrentPlayer().adjustResources(payment.getResourceDelta().negate());
                    game.getCurrentPlayer().adjustIncome(payment.getIncomeDelta().negate());
                }
            }

            @Override
            public void redo() {
                if (isUndoable()) {
                    game.getCurrentPlayer().adjustResources(payment.getResourceDelta());
                    game.getCurrentPlayer().adjustIncome(payment.getIncomeDelta());
                    super.redo();
                }
            }
        };
    }
}
