package tm.effect;

import java.awt.Color;
import java.awt.Graphics;

import tm.Card;
import tm.ImageCache;

public interface ValueEffect {

    default int getSteelDelta() {
        return 0;
    }

    default int getTitaniumDelta() {
        return 0;
    }

    static void render(Graphics g, int x, int y, int steelDelta, int titaniumDelta) {
        g.setFont(Card.FONT);
        g.setColor(Color.WHITE);
        g.drawString("Effect:", x, y);
        g.setColor(new Color(0xFFFF00));
        int currentX = x;
        if (steelDelta > 0) {
            g.drawImage(ImageCache.getImage("images/icon_steel.png"), currentX, y + 5, null);
            g.drawString("+" + steelDelta, currentX + 24, y + 17);
            currentX += 50;
        }
        if (titaniumDelta > 0) {
            g.drawImage(ImageCache.getImage("images/icon_titanium.png"), currentX, y + 5, null);
            g.drawString("+" + titaniumDelta, currentX + 24, y + 17);
        }
    }
}
