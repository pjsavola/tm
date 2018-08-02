package tm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Resources {
    public static final Resources EMPTY = new Resources(0);
    public static final Resources MONEY = new Resources(1);
    public static final Resources MONEY_2 = new Resources(2);
    public static final Resources MONEY_3 = new Resources(3);
    public static final Resources MONEY_4 = new Resources(4);
    public static final Resources STEEL = new Resources(0, 1, 0, 0, 0, 0);
    public static final Resources STEEL_2 = new Resources(0, 2, 0, 0, 0, 0);
    public static final Resources TITANIUM = new Resources(0, 0, 1, 0, 0, 0);
    public static final Resources TITANIUM_2 = new Resources(0, 0, 2, 0, 0, 0);
    public static final Resources PLANT = new Resources(0, 0, 0, 1, 0, 0);
    public static final Resources PLANT_2 = new Resources(0, 0, 0, 2, 0, 0);
    public static final Resources PLANT_3 = new Resources(0, 0, 0, 3, 0, 0);
    public static final Resources ENERGY = new Resources(0, 0, 0, 0, 1, 0);
    public static final Resources HEAT = new Resources(0, 0, 0, 0, 0, 1);

    private final int money;
    private final int steel;
    private final int titanium;
    private final int plants;
    private final int energy;
    private final int heat;

    public Resources(int money) {
        this(money, 0, 0, 0, 0, 0);
    }

    public Resources(int money, int steel, int titanium, int plants, int energy, int heat) {
        this.money = money;
        this.steel = steel;
        this.titanium = titanium;
        this.plants = plants;
        this.energy = energy;
        this.heat = heat;
    }

    public Resources negate() {
        return new Resources(-money, -steel, -titanium, -plants, -energy, -heat);
    }

    public Resources combine(Resources resources) {
        return new Resources(money + resources.money, steel + resources.steel, titanium + resources.titanium,
            plants + resources.plants, energy + resources.energy, heat + resources.heat);
    }

    public boolean isValidState() {
        return money >= 0 && steel >= 0 && titanium >= 0 && plants >= 0 && energy >= 0 && heat >= 0;
    }

    public boolean isValidIncome() {
        return steel >= 0 && titanium >= 0 && plants >= 0 && energy >= 0 && heat >= 0;
    }

    public Resources getTurnIncome(int terraformingRating, Resources leftOverResources) {
        return new Resources(money + terraformingRating, steel, titanium, plants, energy, heat + leftOverResources.energy);
    }

    public int getMoney() {
        return money;
    }

    public int getSteel() {
        return steel;
    }

    public int getTitanium() {
        return titanium;
    }

    public int getEnergy() {
        return energy;
    }

    public int getHeat() {
        return heat;
    }

    // Rendering code below

    private static final Color MONEY_COLOR = new Color(0xFFFF00);
    private static final Color STEEL_COLOR = new Color(0x8B4513);
    private static final Color TITANIUM_COLOR = new Color(0x888888);
    private static final Color PLANT_COLOR = new Color(0x00FF00);
    private static final Color ENERGY_COLOR = new Color(0xBB66FF);
    private static final Color HEAT_COLOR = new Color(0xFF9900);

    public Point render(Graphics g, int x, int y, boolean income) {
        int currentY = y;
        int maxX = x;
        if (money != 0) {
            final Point p = renderMoney(g, x, currentY, income, true);
            currentY = p.y + 2;
            if (p.x > maxX) {
                maxX = p.x;
            }
        }
        if (steel != 0) {
            final Point p = renderSteel(g, x, currentY, income, true);
            currentY = p.y + 2;
            if (p.x > maxX) {
                maxX = p.x;
            }
        }
        if (titanium != 0) {
            final Point p = renderTitanium(g, x, currentY, income, true);
            currentY = p.y + 2;
            if (p.x > maxX) {
                maxX = p.x;
            }
        }
        if (plants != 0) {
            final Point p = renderPlants(g, x, currentY, income, true);
            currentY = p.y + 2;
            if (p.x > maxX) {
                maxX = p.x;
            }
        }
        if (energy != 0) {
            final Point p = renderEnergy(g, x, currentY, income, true);
            currentY = p.y + 2;
            if (p.x > maxX) {
                maxX = p.x;
            }
        }
        if (heat != 0) {
            final Point p = renderHeat(g, x, currentY, income, true);
            currentY = p.y + 2;
            if (p.x > maxX) {
                maxX = p.x;
            }
        }
        if (currentY > y) {
            currentY -= 2; // Last spacing not needed
        }
        return new Point(maxX, currentY);
    }

    public Point renderMoney(Graphics g, int x, int y, boolean income, boolean renderText) {
        return renderField(g, x, y, "images/icon_money.png", money, income, renderText, MONEY_COLOR);
    }

    public Point renderSteel(Graphics g, int x, int y, boolean income, boolean renderText) {
        return renderField(g, x, y, "images/icon_steel.png", steel, income, renderText, STEEL_COLOR);
    }

    public Point renderTitanium(Graphics g, int x, int y, boolean income, boolean renderText) {
        return renderField(g, x, y, "images/icon_titanium.png", titanium, income, renderText, TITANIUM_COLOR);
    }

    public Point renderPlants(Graphics g, int x, int y, boolean income, boolean renderText) {
        return renderField(g, x, y, "images/icon_plant.png", plants, income, renderText, PLANT_COLOR);
    }

    public Point renderEnergy(Graphics g, int x, int y, boolean income, boolean renderText) {
        return renderField(g, x, y, "images/icon_energy.png", energy, income, renderText, ENERGY_COLOR);
    }

    public Point renderHeat(Graphics g, int x, int y, boolean income, boolean renderText) {
        return renderField(g, x, y, "images/icon_heat.png", heat, income, renderText, HEAT_COLOR);
    }

    public static void renderIncomeBorders(Graphics g, int x, int y) {
        g.setColor(new Color(0xCD853F));
        g.drawRect(x, y, 18, 18);
    }

    private static Point renderField(Graphics g, int x, int y, String imagePath, int amount, boolean income, boolean renderText, Color color) {
        if (income) {
            if (amount == 0 && renderText) {
                return new Point(x, y + 18);
            }
            renderIncomeBorders(g, x, y);
        }
        g.drawImage(ImageCache.getImage(imagePath), x + 1, y + 1, null);
        if (renderText) {
            g.setColor(color);
            final Point p = Renderer.renderText(g, Integer.toString(amount), x + 30, y + 4, false);
            return new Point(p.x, Math.max(p.y, y + 18));
        } else {
            return new Point(x + 18, y + 18);
        }
    }
}
