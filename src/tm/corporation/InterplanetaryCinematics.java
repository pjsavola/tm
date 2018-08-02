package tm.corporation;

import java.awt.Graphics;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.Corporation;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ResourceDeltaAction;
import tm.effect.PlayCardEffect;

public class InterplanetaryCinematics extends Corporation implements PlayCardEffect {

    public InterplanetaryCinematics() {
        super("Interplanetary Cinematics", Tags.BUILDING, true);
    }

    @Override
    public Resources getResourceDelta() {
        return new Resources(30, 20, 0, 0, 0, 0);
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        int currentX = Tags.EVENT.render(g, x, y, true).x;
        Resources.MONEY_2.render(g, currentX + 4, y, false);
    }

    @Nullable
    @Override
    public Action cardPlayed(Card card) {
        if (card.getTags().has(Tags.Type.EVENT)) {
            return new ResourceDeltaAction(Resources.MONEY_2);
        }
        return null;
    }
}
