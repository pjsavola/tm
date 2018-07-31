
package tm;

import java.awt.Graphics;

public interface Renderable {

    void renderTitle(Graphics g, int x, int y);

    void renderContent(Graphics g, int x, int y, Game game);
}
