package tm.requirement;

import java.awt.Graphics;

import tm.Game;
import tm.Resources;

public class ProductionRequirement implements Requirement {

    private final Resources production;

    public ProductionRequirement(Resources production) {
        this.production = production;
    }

    @Override
    public boolean check(Game game, int tolerance) {
        return game.getCurrentPlayer().canAdjustIncome(production.negate());
    }

    @Override
    public void render(Graphics g, int x, int y) {

    }
}
