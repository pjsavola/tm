package tm.corporation;

import java.awt.Graphics;

import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.DrawCardsAction;
import tm.effect.RequirementEffect;

public class Inventrix extends Corporation implements RequirementEffect {

    public Inventrix() {
        super("Inventrix", Tags.SCIENCE, true);
    }

    @Override
    public Resources getResourceDelta() {
        return new Resources(45);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new DrawCardsAction(3, false, false);
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        RequirementEffect.render(g, x, y, getTolerance());
    }

    @Override
    public int getTolerance() {
        return 2;
    }
}
