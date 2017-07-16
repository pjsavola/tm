package tm;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ActionHandler {
	private final Deque<Action> undoStack = new ArrayDeque<>();
	private final Deque<Action> redoStack = new ArrayDeque<>();
	private int pendingActionCount = 0;
	private List<Action> pendingActions;
	private final ActionPool pool;
	private Action currentAction;
	private Game game;
	
	public ActionHandler(final Game game) {
		this.game = game;
		this.pool = new ActionPool(game);
	}

	public void actionFinished(final Action action) {
		if (currentAction == action ||
			(currentAction instanceof ActionChain && ((ActionChain) currentAction).complete(action))) {
			currentAction.complete();
			undoStack.push(currentAction);
			redoStack.clear();
			if (pendingActions == null) {
				currentAction = null;
			} else {
				pendingActionCount++;
				if (pendingActions.isEmpty()) {
					final Action[] actions = new Action[pendingActionCount];
					while (pendingActionCount > 0) {
						actions[--pendingActionCount] = undoStack.pop();
					}
					undoStack.push(new ActionChain(' ', actions));
					pendingActions = null;
					currentAction = null;
				} else {
					currentAction = pendingActions.remove(0);
					currentAction.begin();
				}
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
	
	public void undo() {
		if (currentAction != null) {
			cancel();
		} else if (!undoStack.isEmpty()) {
			final Action lastAction = undoStack.pop();
			lastAction.undo();
			redoStack.push(lastAction);
		}
	}
	
	public void redo() {
		if (currentAction == null) {
			if (!redoStack.isEmpty()) {
				final Action lastAction = redoStack.pop();
				lastAction.redo();
				undoStack.push(lastAction);
			}
		}
	}
	
	public void cancel() {
		if (currentAction != null) {
			currentAction.cancel();
			currentAction = null;
			if (pendingActions != null) {
				while (pendingActionCount-- > 0) {
					undoStack.pop().undo();
				}
				pendingActions = null;
			}
		}
	}
	
	public void render(final Graphics g) {
    	if (currentAction != null) {
    		currentAction.paint(g);
    	}
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
					cancel();
					currentAction = pool.getActionMap().get(arg0.getKeyChar());
					if (currentAction != null) {
						currentAction.begin();
					}
				}
				game.repaint();
			}
        };
	}
}
