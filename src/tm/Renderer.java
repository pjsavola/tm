package tm;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

public abstract class Renderer {
    public static final Color VP_CIRCLE_COLOR = new Color(0x8B4513);

    // 24 x 24
    public static Point renderVPCircle(Graphics g, int x, int y) {
        g.setColor(VP_CIRCLE_COLOR);
        g.fillOval(x, y, 24, 24);
        return new Point(24, 24);
    }

    // 12 x 12
    public static Point renderMarker(Graphics g, int x, int y) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y, 12, 12);
        g.setColor(Color.GRAY);
        g.drawRect(x, y, 12, 12);
        return new Point(12, 12);
    }

    public static void renderX(Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.RED);
        g.drawLine(x, y, x + width, y + height);
        g.drawLine(x, y + height, x + width, y);
    }

    public static Point renderText(Graphics g, String text, int x, int y, boolean centered) {
        final FontMetrics fontMetrics = g.getFontMetrics();
        final int height = fontMetrics.getHeight();
        final int width = fontMetrics.stringWidth(text);
        if (centered) {
            g.drawString(text, x - width / 2, y + height / 3);
            return new Point(x, y);
        } else {
            g.drawString(text, x, y + 2 * height / 3);
            return new Point(x + width, y + 2 * height / 3);
        }
    }

    public static Point renderImage(Graphics g, String path, int x, int y) {
        final BufferedImage image = ImageCache.getImage(path);
        g.drawImage(image, x, y, null);
        return new Point(x + image.getWidth(), image.getHeight());
    }

    public static Point renderIcon(Graphics g, Tile.Type type, int x, int y) {
        final Image image = type.getIcon();
        g.drawImage(image, x, y, null);
        return new Point(x + image.getWidth(null), y + image.getHeight(null));
    }

    public static Point renderSmallIcon(Graphics g, Tile.Type type, int x, int y) {
        final Image image = type.getSmallIcon();
        g.drawImage(image, x, y, null);
        return new Point(x + image.getWidth(null), y + image.getHeight(null));
    }
}
