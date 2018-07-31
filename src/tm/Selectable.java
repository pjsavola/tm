
package tm;

import java.awt.Graphics;

public interface Selectable {

    default Resources getResourceDelta(Game game) {
        return Resources.EMPTY;
    }

    default Resources getIncomeDelta(Game game) {
        return Resources.EMPTY;
    }

    void renderTitle(Graphics g, int x, int y);

    void renderContent(Graphics g, int x, int y, Game game);
}
