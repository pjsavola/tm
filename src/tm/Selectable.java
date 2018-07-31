
package tm;

import java.awt.Graphics;

public interface Selectable {

    void renderTitle(Graphics g, int x, int y);

    void renderContent(Graphics g, int x, int y, Game game);
}
