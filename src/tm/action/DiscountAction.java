package tm.action;

import com.sun.istack.internal.Nullable;
import tm.Corporation;
import tm.Game;
import tm.Player;
import tm.Resources;
import tm.completable.Completable;
import tm.completable.InstantCompletable;

public class DiscountAction implements Action {

	final Resources delta;
	@Nullable
    final Class<? extends Corporation> corporation;

	public DiscountAction(Resources delta, @Nullable Class<? extends Corporation> corporation) {
		this.delta = delta;
		this.corporation = corporation;
	}

	@Override
	public Completable begin(Game game) {
		final Player player = game.getCurrentPlayer();
		final Resources discount;
		if (game.getCurrentPlayer().getCorporation().getClass().equals(corporation)) {
			discount = delta;
		} else {
			discount = new Resources(0);
		}
		return new InstantCompletable(game) {
			@Override
			public void complete() {
				player.adjustResources(discount);
			}

			@Override
			public void undo() {
				player.adjustResources(discount.negate());
			}

			@Override
			public void redo() {
				player.adjustResources(discount);
			}
		};
	}
}
