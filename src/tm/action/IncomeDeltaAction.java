package tm.action;

import java.awt.Graphics;

import com.sun.istack.internal.Nullable;
import tm.Game;
import tm.Player;
import tm.Resources;
import tm.completable.Completable;
import tm.completable.InstantCompletable;

public class IncomeDeltaAction implements Action {

    @Nullable
    final Resources delta;

    public IncomeDeltaAction(Resources delta) {
        this.delta = delta;
    }

    @Nullable
    public Resources getDelta(Game game) {
        return delta;
    }

    @Override
    public boolean check(Game game) {
        final Player targetPlayer = game.getCurrentPlayer();
        return targetPlayer.canAdjustIncome(delta);
    }

    @Override
    public Completable begin(Game game) {
        final Player targetPlayer = game.getCurrentPlayer();
        final Resources delta = getDelta(game);
        return new InstantCompletable(game) {
            @Override
            public void complete() {
                targetPlayer.adjustIncome(delta);
            }

            @Override
            public void undo() {
                targetPlayer.adjustIncome(delta.negate());
            }

            @Override
            public void redo() {
                targetPlayer.adjustIncome(delta);
            }
        };
    }

    @Override
    public void render(Graphics g, int x, int y, Game game) {
        delta.render(g, x, y, true);
    }
}
