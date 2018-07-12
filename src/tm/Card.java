package tm;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Card {

    public static final int WIDTH = 200;
    public static final int TITLE_HEIGHT = 16;
    public static final int CARD_HEIGHT = 200;
    private static final Color TEXT_COLOR = new Color(0xFFFFFF);
    private static final Color BG_COLOR = new Color(0x333333);
    private static final Font FONT = new Font("Arial", Font.BOLD, 12);
    private final String name;
    private final int cost;
    private final Tags tags;
    private final boolean effect;

    public Card(String name, int cost, Tags tags, boolean effect) {
        this.name = name;
        this.cost = cost;
        this.tags = tags;
        this.effect = effect;
    }

    public Color getBorderColor() {
        if (tags.hasEvent()) {
            return Color.RED;
        } else if (effect) {
            return Color.BLUE;
        } else if (cost == 0) {
            return Color.WHITE;
        } else {
            return Color.GREEN;
        }
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public Tags getTags() {
        return tags;
    }

    public void renderTitle(Graphics g, int x, int y) {
        g.setFont(FONT);
        final FontMetrics metrics = g.getFontMetrics();
        final int h = metrics.getHeight();
        final int w = metrics.stringWidth(name);

        // Draw background
        g.setColor(BG_COLOR);
        g.fillRect(x, y, WIDTH, TITLE_HEIGHT);
        
        // Draw border
        g.setColor(getBorderColor());
        g.drawRect(x, y, WIDTH, TITLE_HEIGHT);

        // Draw text
        g.setColor(TEXT_COLOR);
        g.drawString(name, x + (WIDTH - w) / 2, y + 12);

        // Draw cost
        if (cost > 0) {
            final String costString = Integer.toString(cost);
            final int costWidth = metrics.stringWidth(costString);
            g.drawImage(ImageCache.getImage("images/icon_money.png"), x, y, null);
            g.setColor(Color.BLACK);
            g.drawString(costString, x + (16 - costWidth) / 2, y + 12);
        }

        // Draw tags
        tags.render(g, x + WIDTH, y);
    }

    // x: top-left corner
    // y: top-right corner
    public void renderContent(Graphics g, int x, int y) {
        // Draw background
        g.setColor(BG_COLOR);
        g.fillRect(x, y, WIDTH, CARD_HEIGHT);

        // Draw border
        g.setColor(getBorderColor());
        g.drawRect(x, y, WIDTH, CARD_HEIGHT);

        // Draw content
        g.setColor(TEXT_COLOR);
        g.drawString("jee jee", x + 4, y + 16);
    }
}
