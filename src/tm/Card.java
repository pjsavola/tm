package tm;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Card {

    public static final int WIDTH = 200;
    public static final int TITLE_HEIGHT = 16;
    private static final Color TEXT_COLOR = new Color(0xFFFFFF);
    private static final Color BG_COLOR = new Color(0x333333);
    private static final Font FONT = new Font("Arial", Font.BOLD, 12);
    private final Tags tags;

    public Card(final Tags tags) {
        this.tags = tags.space().event();
    }

    public Color getBorderColor() {
        return new Color(0xFF0000);
    }

    public String getTitle() {
        return "Card";
    }

    public int getCost() { return 25; }

    public Tags getTags() {
        return tags;
    }

    public void renderTitle(final Graphics g, final int x, final int y) {
        g.setFont(FONT);
        final FontMetrics metrics = g.getFontMetrics();
        final int h = metrics.getHeight();
        final int w = metrics.stringWidth(getTitle());

        // Draw background
        g.setColor(BG_COLOR);
        g.fillRect(x, y, WIDTH, TITLE_HEIGHT);
        
        // Draw border
        g.setColor(getBorderColor());
        g.drawRect(x, y, WIDTH, TITLE_HEIGHT);

        // Draw text
        g.setColor(TEXT_COLOR);
        g.drawString(getTitle(), x + (WIDTH - w) / 2, y + 12);

        // Draw cost
        final String cost = Integer.toString(getCost());
        final int costWidth = metrics.stringWidth(cost);
        g.drawImage(ImageCache.getImage("images/icon_money.png"), x, y, null);
        g.setColor(Color.BLACK);
        g.drawString(cost, x + (16 - costWidth) / 2, y + 12);

        // Draw tags
        tags.render(g, x + WIDTH, y);
    }
}
