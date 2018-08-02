package tm;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

public abstract class Renderer {
    public static final Color VP_CIRCLE_COLOR = new Color(0x8B4513);

    // 24 x 24
    public static int renderVPCircle(Graphics g, int x, int y) {
        g.setColor(VP_CIRCLE_COLOR);
        g.fillOval(x, y, 24, 24);
        return 24;
    }

    // 12 x 12
    public static int renderMarker(Graphics g, int x, int y) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y, 12, 12);
        g.setColor(Color.GRAY);
        g.drawRect(x, y, 12, 12);
        return 12;
    }

    public static int renderText(Graphics g, String text, int x, int y, boolean centered) {
        final FontMetrics fontMetrics = g.getFontMetrics();
        final int height = fontMetrics.getHeight();
        final int width = fontMetrics.stringWidth(text);
        if (centered) {
            g.drawString(text, x - width / 2, y + height / 3);
        } else {
            g.drawString(text, x, y + height / 3);
        }
        return width;
    }
}
