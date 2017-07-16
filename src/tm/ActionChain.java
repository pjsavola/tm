package tm;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class ActionChain implements Action {

	final char key;
	final Action[] actions;
	final List<Action> finishedActions = new ArrayList<>();
	
	public ActionChain(final char key, final Action ... actions) {
		this.key = key;
		this.actions = actions;
	}
	
	public boolean complete(final Action action) {
		finishedActions.add(action);
		return finishedActions.size() == actions.length;
	}
	
	@Override
	public char getKey() {
		return key;
	}
	
	@Override
	public boolean check() {
		for (final Action action : actions) {
			if (!action.check()) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void begin() {
		for (final Action action : actions) {
			action.begin();
		}
	}

	@Override
	public void cancel() {
		for (final Action action : actions) {
			action.cancel();
		}
	}

	@Override
	public void complete() {
		for (final Action action : actions) {
			action.complete();
		}
	}

	@Override
	public void undo() {
		for (int i = actions.length - 1; i >= 0; i--) {
			actions[i].undo();
		}
	}

	@Override
	public void redo() {
		for (final Action action : actions) {
			action.redo();
		}
	}

	@Override
	public void paint(Graphics g) {
		for (final Action action : actions) {
			action.paint(g);
		}
	}
}
