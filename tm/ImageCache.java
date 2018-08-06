package tm;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public abstract class ImageCache {
    private static Map<String, BufferedImage> imageCache = new HashMap<>();

    public static BufferedImage getImage(String relativePath) {
        final String path = relativePath.startsWith("/") ? relativePath : "/" + relativePath;
        BufferedImage image = imageCache.get(path);
        if (image != null) {
            return image;
        }
        try (InputStream is = ImageCache.class.getResourceAsStream(path)) {
            image = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException("Image " + path + " is missing");
        }
        return updateCache(image, path, imageCache);
    }

    private static <T> BufferedImage updateCache(BufferedImage image, T key, Map<T, BufferedImage> cache) {
        final BufferedImage compatibleImage = toCompatibleImage(image);
        cache.put(key, compatibleImage);
        return compatibleImage;
    }

    private static BufferedImage toCompatibleImage(BufferedImage image) {
        final GraphicsConfiguration gc = getConfiguration();
        if (image.getColorModel().equals(gc.getColorModel())) {
            return image;
        }
        final BufferedImage compatibleImage = gc.createCompatibleImage(
            image.getWidth(), image.getHeight(),
            image.getTransparency()
        );
        final Graphics g = compatibleImage.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return compatibleImage;
    }

    private static GraphicsConfiguration getConfiguration() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    }
}