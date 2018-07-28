package tm.effect;

import java.awt.Graphics;

import tm.ImageCache;
import tm.Player;

public interface ScoringEffect {
    int getVPs(Player player);

    default void render(Graphics g, int x, int y) {
        g.drawImage(ImageCache.getImage("images/tag_jovian.png"), x, y, null);
    }
}
