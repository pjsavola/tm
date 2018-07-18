package tm.completable;

import java.awt.Graphics;
import java.util.Set;

import tm.Game;

public class CompletableChain implements Completable {
	
	private final Completable[] completables;
	
	public CompletableChain(Game game, Completable[] completables) {
		this.completables = completables;
	}
	
	@Override
	public boolean remove(Set<Completable> completedSet) {
		for (Completable completable : completables) {
			if (!completedSet.contains(completable)) {
				return false;
			}
		}
		for (Completable completable : completables) {
			completedSet.remove(completable);	
		}
		return true;
	}
	
	@Override
	public void cancel() {
		for (Completable completable : completables) {
			completable.cancel();
		}
	}

	@Override
	public void complete() {
		for (Completable completable : completables) {
			completable.complete();
		}
	}

	@Override
	public void undo() {
		for (int i = completables.length - 1; i >= 0; i--) {
			completables[i].undo();
		}
	}

	@Override
	public void redo() {
		for (Completable completable : completables) {
			completable.redo();
		}
	}

	@Override
	public void paint(Graphics g) {
		for (Completable completable : completables) {
			completable.paint(g);
		}
	}

	@Override
	public boolean pressKey(char c) {
		for (Completable completable : completables) {
			if (completable.pressKey(c)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean adjustPayment(boolean steel, boolean increment) {
		for (Completable completable : completables) {
			if (completable.adjustPayment(steel, increment)) {
				return true;
			}
		}
		return false;
	}
}