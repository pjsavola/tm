package tm;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Planet {
	private final static Font font = new Font("Arial", Font.BOLD, 12);
	private int waterCount = 9;
	private int temperature = -30;
	private int oxygen = 0;
	
	public int getWaterCount() {
		return waterCount;
	}
	
	public int getOxygen() {
		return oxygen;
	}
	
	public int getTemperature() {
		return temperature;
	}
	
	public void adjustWaterCount(final int delta) {
		waterCount += delta;
	}
	
	public int adjustOxygen(final int delta) {
		int oxygenBeforeAdjusting = oxygen;
		oxygen = Math.min(14, oxygen + delta);
		return oxygen - oxygenBeforeAdjusting;
	}
	
	public int adjustTemperature(final int delta) {
		int temperatureBeforeAdjusting = temperature;
		temperature = Math.min(8, temperature + delta);
		return temperature - temperatureBeforeAdjusting;
	}
	
	public void render(final Graphics g) {
    	final Color oldColor = g.getColor();
		g.setFont(font);
		renderText(g, "Temperature", temperature, 1, getColor(-30, temperature, 8, new Color(0x6666FF), new Color(0xFF3300)));
		renderText(g, "Oxygen", oxygen, 2, getColor(0, oxygen, 14, new Color(0x8B4513), new Color(0xBBBBFF)));
		renderText(g, "Water left", waterCount, 3, new Color(0x0000FF));
		g.setColor(oldColor);
	}
	
    private Color getColor(int min, double now, int max, Color color1, Color color2) {
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
    
	private static void renderText(final Graphics g, final String name, final int amount, final int i, final Color color) {
		g.setColor(color);
		final String text = name + ": " + amount;
		final FontMetrics metrics = g.getFontMetrics();
		int w = metrics.stringWidth(text);
        int h = metrics.getHeight();
        g.drawString(text, 698 - w, (h + 1) * i);
	}

}
