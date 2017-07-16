package tm;

import java.awt.Graphics;

public interface Action {
	char getKey();
	boolean check();
	void begin();
	void cancel();
	void complete();
	void undo();
	void redo();
	void paint(Graphics g);
}
