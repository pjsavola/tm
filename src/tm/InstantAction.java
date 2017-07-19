package tm;

import java.awt.Graphics;

public abstract class InstantAction implements Action {
	
	private static final long serialVersionUID = 1L;
	private final Game game;
	
	protected InstantAction(final Game game) {
		this.game = game;
	}
	
	@Override
	public char getKey() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void begin() {
		game.getActionHandler().actionFinished(this);
	}

	@Override
	public void cancel() {
	}

	@Override
	public void paint(Graphics g) {
	}
}

