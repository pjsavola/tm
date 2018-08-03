
package tm;

import java.awt.Graphics;

public interface Selectable {

    void renderTitle(Graphics g, int x, int y, Game game);

    void renderContent(Graphics g, int x, int y, Game game);
}
