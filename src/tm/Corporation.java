package tm;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public abstract class Corporation extends Card {

    public static final int WIDTH = 200;
    public static final int TITLE_HEIGHT = 16;
    private static final Color TEXT_COLOR = new Color(0xFFFFFF);
    private static final Color BG_COLOR = new Color(0x000000);
    private static final Font FONT = new Font("Arial", Font.BOLD, 12);

    protected Corporation(final Tags tags) {
        super(tags);
    }

    @Override
    public Color getBorderColor() {
        return new Color(0x999999);
    }

    @Override
    public int getCost() {
        throw new UnsupportedOperationException();
    }

    public abstract Resources getInitialResources();

    @Override
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
    }
}
