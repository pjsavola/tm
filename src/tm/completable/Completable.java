package tm.completable;

import java.awt.Graphics;
import java.util.Set;

public interface Completable {
	boolean remove(final Set<Completable> completedSet);
	void cancel();
	void complete();
	void undo();
	void redo();
	void paint(Graphics g);
}
