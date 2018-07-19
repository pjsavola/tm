package tm.action;

import tm.Game;
import tm.completable.Completable;
import tm.completable.InstantCompletable;

public class BlankAction implements Action {

	@Override
	public Completable begin(Game game) {
		return new InstantCompletable(game) {
			@Override
			public void complete() {
			}
			@Override
			public void undo() {
			}
			@Override
			public void redo() {
			}
		};
	}
}
