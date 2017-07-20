package tm;

import java.awt.Graphics;
import java.util.Set;

public abstract class InstantCompletable implements Completable {
	
	public InstantCompletable(final Game game) {
		game.getActionHandler().completed(this);
	}
	
	@Override
	public boolean remove(final Set<Completable> completedSet) {
		return completedSet.remove(this);
	}
	
	@Override
	public void cancel() {
	}
	
	@Override
	public void paint(Graphics g) {
	}
}
