package tm.requirement;

import tm.Game;
import tm.Resources;

public abstract class ProductionRequirement implements Requirement {

    private final Resources production;

    public ProductionRequirement(Resources production) {
        this.production = production;
    }

    @Override
    public boolean check(Game game, int tolerance) {
        return game.getCurrentPlayer().canAdjustIncome(production.negate());
    }
}
