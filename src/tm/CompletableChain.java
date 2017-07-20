package tm;

import java.awt.Graphics;
import java.util.Set;

public class CompletableChain implements Completable {
	
	private final Completable[] completables;
	
	public CompletableChain(final Game game, final Completable[] completables) {
		this.completables = completables;
	}
	
	@Override
	public boolean remove(final Set<Completable> completedSet) {
		for (final Completable completable : completables) {
			if (!completedSet.contains(completable)) {
				return false;
			}
		}
		for (final Completable completable : completables) {
			completedSet.remove(completable);	
		}
		return true;
	}
	
	@Override
	public void cancel() {
		for (final Completable completable : completables) {
			completable.cancel();
		}
	}

	@Override
	public void complete() {
		for (final Completable completable : completables) {
			completable.complete();
		}
	}

	@Override
	public void undo() {
		for (final Completable completable : completables) {
			completable.undo();
		}
	}

	@Override
	public void redo() {
		for (final Completable completable : completables) {
			completable.redo();
		}
	}

	@Override
	public void paint(final Graphics g) {
		for (final Completable completable : completables) {
			completable.paint(g);
		}
	}
}