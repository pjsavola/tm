package tm.completable;

import tm.Game;

public abstract class InstantCompletable implements Completable {
	
	public InstantCompletable(Game game) {
		game.getActionHandler().completed(this);
	}
}
