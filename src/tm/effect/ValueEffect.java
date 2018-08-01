package tm.effect;

import java.awt.Color;
import java.awt.Graphics;

import tm.ImageCache;

public interface ValueEffect {

    default int getSteelDelta() {
        return 0;
    }

    default int getTitaniumDelta() {
        return 0;
    }

    static void render(Graphics g, int x, int y, int steelDelta, int titaniumDelta) {
        g.setColor(new Color(0xFFFF00));
        int currentX = x;
        if (steelDelta > 0) {
            g.drawImage(ImageCache.getImage("images/icon_steel.png"), currentX, y, null);
            g.drawString("+" + steelDelta, currentX + 24, y + 12);
            currentX += 50;
        }
        if (titaniumDelta > 0) {
            g.drawImage(ImageCache.getImage("images/icon_titanium.png"), currentX, y, null);
            g.drawString("+" + titaniumDelta, currentX + 24, y + 12);
        }
    }
}
