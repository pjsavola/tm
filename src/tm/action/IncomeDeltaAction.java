package tm.action;

import com.sun.istack.internal.Nullable;
import tm.Game;
import tm.Player;
import tm.Resources;
import tm.completable.Completable;
import tm.completable.InstantCompletable;

public class IncomeDeltaAction implements Action {
	
	final Resources delta;
	@Nullable
	final Player player;
	
	public IncomeDeltaAction(Resources delta) {
		this(delta, null);
	}

	public IncomeDeltaAction(Resources delta, @Nullable Player player) {
		this.delta = delta;
		this.player = player;
	}

    @Override
    public boolean check(Game game) {
        final Player targetPlayer = player == null ? game.getCurrentPlayer() : player;
        return targetPlayer.canAdjustIncome(delta);
    }

	@Override
	public Completable begin(Game game) {
		final Player targetPlayer = player == null ? game.getCurrentPlayer() : player;
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
}
