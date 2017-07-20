package tm;


public class ActionChain implements Action {

	final char key;
	final String description;
	final Action[] actions;
	
	public ActionChain(final Action ... actions) {
		this(' ', "", actions);
	}
	
	public ActionChain(final char key, final String description, final Action ... actions) {
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
	public boolean check(final Game game) {
		for (final Action action : actions) {
			if (!action.check(game)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public Completable begin(final Game game) {
		final Completable[] completables = new Completable[actions.length];
		for (int i = 0; i < actions.length; i++) {
			completables[i] = actions[i].begin(game);
		}
		return new CompletableChain(game, completables);
	}
}
