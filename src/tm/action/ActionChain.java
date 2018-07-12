package tm.action;


import java.util.ArrayList;
import java.util.List;

import tm.Game;
import tm.completable.Completable;
import tm.completable.CompletableChain;

public class ActionChain implements Action {

	final char key;
	final String description;
	final Action[] actions;
	
	public ActionChain(Action ... actions) {
		this(' ', "", actions);
	}
	
	public ActionChain(char key, String description, Action ... actions) {
		this.key = key;
		this.description = description;
		this.actions = actions;
	}
	
	@Override
	public char getKey() {
		return key;
	}
	
	public String getDescription() {
		return description;
	}
	
	@Override
	public boolean check(Game game) {
		for (Action action : actions) {
			if (!action.check(game)) {
				if (!action.isOptional()) {
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public Completable begin(Game game) {
		final List<Completable> completables = new ArrayList<>();
		for (Action action : actions) {
			if (!action.isOptional() || action.check(game)) {
				completables.add(action.begin(game));
			}
		}
		return new CompletableChain(game, completables.toArray(new Completable[completables.size()]));
	}
}
