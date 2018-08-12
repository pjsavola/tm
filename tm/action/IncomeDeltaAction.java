package tm.action;

import java.awt.Graphics;
import java.awt.Point;

import javax.annotation.Nullable;
import tm.Game;
import tm.Player;
import tm.Resources;
import tm.completable.Completable;
import tm.completable.InstantCompletable;

public class IncomeDeltaAction implements Action {

    @Nullable
    final Resources delta;

    public IncomeDeltaAction(@Nullable Resources delta) {
        this.delta = delta;
    }

    public Resources getDelta(Game game) {
        return delta;
    }

    @Override
    public boolean check(Game game) {
        final Player targetPlayer = game.getCurrentPlayer();
        return targetPlayer.canAdjustIncome(getDelta(game));
    }

    @Override
    public Completable begin(Game game) {
        final Player targetPlayer = game.getCurrentPlayer();
        return new InstantCompletable(game) {
            @Override
            public void complete() {
                targetPlayer.adjustIncome(getDelta(game));
            }

            @Override
            public void undo() {
                targetPlayer.adjustIncome(getDelta(game).negate());
            }

            @Override
            public void redo() {
                targetPlayer.adjustIncome(getDelta(game));
            }
        };
    }

    @Override
    public Point render(Graphics g, int x, int y, Game game) {
        return getDelta(game).render(g, x, y, true);
    }
}
