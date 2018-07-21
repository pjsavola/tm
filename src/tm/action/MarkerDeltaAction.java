package tm.action;

import tm.Game;
import tm.completable.Completable;
import tm.completable.InstantCompletable;

public class MarkerDeltaAction implements Action {

	final int delta;
	final CardWithMarkers card;

	public MarkerDeltaAction(int delta, CardWithMarkers card) {
		this.delta = delta;
		this.card = card;
	}

	@Override
	public boolean check(Game game) {
		return card.getMarkerCount() + delta >= 0;
	}

	@Override
	public Completable begin(Game game) {
		return new InstantCompletable(game) {
			@Override
			public void complete() {
				card.adjustMarkers(delta);
			}

			@Override
			public void undo() {
				card.adjustMarkers(-delta);
			}

			@Override
			public void redo() {
				card.adjustMarkers(delta);
			}
		};
	}
}
