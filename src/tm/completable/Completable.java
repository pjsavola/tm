package tm.completable;

import java.awt.Graphics;
import java.util.Set;

public interface Completable {

    default boolean remove(Set<Completable> completedSet) {
        return completedSet.remove(this);
    }

    default void cancel() {
    }

    void complete();

    void undo();

    void redo();

    default boolean pressKey(char c) {
        return false;
    }

    default boolean adjustPayment(boolean steel, boolean increment) {
        return false;
    }

    default void paint(Graphics g) {
    }
}
