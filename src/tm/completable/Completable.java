package tm.completable;

import java.awt.Graphics;
import java.util.Set;

public interface Completable {

	default boolean remove(final Set<Completable> completedSet) {
		return completedSet.remove(this);
	}

	default void cancel() {
	}

	void complete();

	void undo();

	void redo();

	default void paint(final Graphics g) {
	}
}
