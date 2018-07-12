package tm.action;

import com.sun.istack.internal.Nullable;
import tm.Game;
import tm.Player;
import tm.Resources;
import tm.completable.Completable;
import tm.completable.InstantCompletable;

public class ResourceDeltaAction implements Action {

	final Resources delta;
	@Nullable
    final Player player;
	
	public ResourceDeltaAction(Resources delta) {
	    this(delta, null);
	}

	public ResourceDeltaAction(Resources delta, @Nullable Player player) {
		this.delta = delta;
		this.player = player;
	}

	@Override
	public boolean check(Game game) {
	    final Player targetPlayer = player == null ? game.getCurrentPlayer() : player;
		return targetPlayer.canAdjustResources(delta);
	}

	@Override
	public Completable begin(Game game) {
        final Player targetPlayer = player == null ? game.getCurrentPlayer() : player;
		return new InstantCompletable(game) {
			@Override
			public void complete() {
				targetPlayer.adjustResources(delta);
			}

			@Override
			public void undo() {
				targetPlayer.adjustResources(delta.negate());
			}

			@Override
			public void redo() {
				targetPlayer.adjustResources(delta);
			}
		};
	}
}
