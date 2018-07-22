package tm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Planet {
    private static final Font font = new Font("Arial", Font.BOLD, 16);
    private int waterCount = 9;
    private int temperature = -30;
    private int oxygen = 0;
    private int round = 1;

    public int getWaterCount() {
        return waterCount;
    }

    public int getOxygen() {
        return oxygen;
    }

    public int getTemperature() {
        return temperature;
    }

    public void adjustWaterCount(int delta) {
        waterCount += delta;
    }

    public int adjustOxygen(int delta) {
        int oxygenBeforeAdjusting = oxygen;
        oxygen = Math.min(14, oxygen + delta);
        return oxygen - oxygenBeforeAdjusting;
    }

    public int adjustTemperature(int delta) {
        int temperatureBeforeAdjusting = temperature;
        temperature = Math.min(8, temperature + delta);
        return temperature - temperatureBeforeAdjusting;
    }

    public void adjustRound(final int delta) {
        round += delta;
    }

    public int getRound() {
        return round;
    }

    public void render(Graphics g) {
        final Color oldColor = g.getColor();
        g.setFont(font);
        final String generation = "Generation " + round;
        final int w = g.getFontMetrics().stringWidth(generation);
        g.setColor(new Color(0xFFFFFF));
        g.drawString(generation, 350 - w / 2, 688);
        g.drawImage(ImageCache.getImage("images/icon_temperature.png"), 658, 2, null);
        g.drawImage(ImageCache.getImage("images/icon_oxygen.png"), 650, 34, null);
        g.drawImage(ImageCache.getImage("images/icon_water.png"), 650, 60, null);
        g.setColor(getColor(-30, temperature, 8, new Color(0x6666FF), new Color(0xFF3300)));
        g.drawString(Integer.toString(temperature), 670, 20);
        g.setColor(getColor(0, oxygen, 14, new Color(0x8B4513), new Color(0xBBBBFF)));
        g.drawString(Integer.toString(oxygen), 680, 52);
        g.setColor(new Color(0x4444FF));
        g.drawString(Integer.toString(waterCount), 680, 78);
        g.setColor(oldColor);
    }

    private static Color getColor(int min, double now, int max, Color color1, Color color2) {
        int r1 = color1.getRed();
        int g1 = color1.getGreen();
        int b1 = color1.getBlue();
        int r2 = color2.getRed();
        int g2 = color2.getGreen();
        int b2 = color2.getBlue();
        double coeff1 = (max - now) / (max - min);
        double coeff2 = (now - min) / (max - min);
        return new Color((int) (coeff1 * r1 + coeff2 * r2),
            (int) (coeff1 * g1 + coeff2 * g2),
            (int) (coeff1 * b1 + coeff2 * b2));
    }
}
