package tm.action;

import tm.Game;
import tm.completable.Completable;

public interface Action {

	default char getKey() {
		throw new UnsupportedOperationException();
	}

	default boolean check(Game game) {
		return true;
	}

	Completable begin(Game game);

	default boolean isOptional() {
		return false;
	}
}
