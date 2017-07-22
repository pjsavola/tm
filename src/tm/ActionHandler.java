package tm;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tm.action.Action;
import tm.completable.Completable;
import tm.completable.CompletableChain;

public class ActionHandler {
	private final Deque<Completable> undoStack = new ArrayDeque<>();
	private final Deque<Completable> redoStack = new ArrayDeque<>();
	private int pendingActionCount;
	private List<Action> pendingActions;
	private List<Action> pendingIrreversibleActions;
	private final ActionPool pool;
	private Completable current;
	private Game game;
	private boolean cancelEnabled = true;
	private Set<Completable> completedActions = new HashSet<>();

	public ActionHandler(final Game game) {
		this.game = game;
		this.pool = new ActionPool(game);
	}

	public void completed(final Completable action) {
		completedActions.add(action);
		if (current != null) {
			if (current.remove(completedActions)) {
				onCompletion();
			}
		}
	}
	
	private void process(final Completable completable) {
		if (completable != null) {
			current = completable;
			if (current.remove(completedActions)) {
				onCompletion();
			}
		}
	}
	
	private void onCompletion() {
		current.complete();
		undoStack.push(current);
		redoStack.clear();
		if (pendingActions == null) {
			current = null;
		} else {
			pendingActionCount++;
			if (pendingActions.isEmpty()) {
				final Completable[] completables = new Completable[pendingActionCount];
				while (pendingActionCount > 0) {
					completables[--pendingActionCount] = undoStack.pop();
				}
				undoStack.push(new CompletableChain(game, completables));
				pendingActions = null;
				current = null;
			} else {
				process(pendingActions.remove(0).begin(game));
			}
		}
		if (current == null && pendingIrreversibleActions != null) {
			if (pendingIrreversibleActions.isEmpty()) {
				cancelEnabled = true;
				pendingIrreversibleActions = null;
			} else {
				undoStack.clear();
				cancelEnabled = false;
				process(pendingIrreversibleActions.remove(0).begin(game));
			}
		}
	}
	
	public void addPendingAction(final Action action) {
		if (pendingActions == null) {
			pendingActions = new ArrayList<>();
			pendingActionCount = 0;
		}
		pendingActions.add(action);
	}

	public void addPendingIrreversibleAction(final Action action) {
		if (pendingIrreversibleActions == null) {
			pendingIrreversibleActions = new ArrayList<>();
		}
		pendingIrreversibleActions.add(action);
	}

	public void reprocess(final Completable completable) {
		cancelEnabled = false;
		pendingIrreversibleActions = new ArrayList<>(0);
		process(completable);
	}

	public boolean canUndo() {
		return !undoStack.isEmpty() || (cancelEnabled && current != null);
	}
	
	public boolean canRedo() {
		return current == null && !redoStack.isEmpty();
	}

	public boolean canAct() {
		return current == null || cancelEnabled;
	}

	public void undo() {
		if (current != null) {
			cancel();
		} else if (!undoStack.isEmpty()) {
			final Completable lastAction = undoStack.pop();
			lastAction.undo();
			redoStack.push(lastAction);
		}
	}
	
	public void redo() {
		if (current == null) {
			if (!redoStack.isEmpty()) {
				final Completable lastAction = redoStack.pop();
				lastAction.redo();
				undoStack.push(lastAction);
			}
		}
	}
	
	public boolean cancel() {
		if (!cancelEnabled) {
			return false;
		}
		if (current != null) {
			current.cancel();
			current = null;
			if (pendingActions != null) {
				while (pendingActionCount-- > 0) {
					undoStack.pop().undo();
				}
				pendingActions = null;
			}
		}
		return true;
	}
	
	public void render(final Graphics g) {
    	if (current != null) {
    		current.paint(g);
    	}
    	pool.render(g);
	}
	
	public KeyListener getKeyListener() {
		return new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
					cancel();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (arg0.getKeyChar() == 'u') {
					undo();
				} else if (arg0.getKeyChar() == 'r') {
					redo();
				} else {
					if (cancel()) {
						process(pool.getCompletable(arg0.getKeyChar()));
					}
				}
				game.repaint();
			}
        };
	}
}
