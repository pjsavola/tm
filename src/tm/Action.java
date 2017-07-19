package tm;

import java.awt.Graphics;
import java.io.Serializable;

public interface Action extends Serializable {
	char getKey();
	boolean check();
	void begin();
	void cancel();
	void complete();
	void undo();
	void redo();
	void paint(Graphics g);
}
