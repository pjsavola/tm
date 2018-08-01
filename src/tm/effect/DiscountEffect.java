package tm.effect;

import java.awt.Color;
import java.awt.Graphics;

import tm.Card;
import tm.ImageCache;

public interface DiscountEffect {
    int getDiscount(Card card);

    static void render(Graphics g, int x, int y, String imagePath, String amount) {
        g.setFont(Card.FONT);
        g.drawImage(ImageCache.getImage(imagePath), x, y, null);
        g.setColor(new Color(0xFFFF00));
        g.drawString(amount, x + 24, y + 12);
    }
}
