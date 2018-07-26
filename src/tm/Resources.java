package tm;

import java.awt.Color;
import java.awt.Graphics;

public class Resources {
    public static final Resources EMPTY = new Resources(0);
    public static final Resources MONEY = new Resources(1);
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
    private static final Color ENERGY_COLOR = new Color(0x6600FF);
    private static final Color HEAT_COLOR = new Color(0xFF9900);

    public void renderMoney(Graphics g, int x, int y, boolean income) {
        renderField(g, x, y, "images/icon_money.png", money, income, MONEY_COLOR);
    }

    public void renderSteel(Graphics g, int x, int y, boolean income) {
        renderField(g, x, y, "images/icon_steel.png", steel, income, STEEL_COLOR);
    }

    public void renderTitanium(Graphics g, int x, int y, boolean income) {
        renderField(g, x, y, "images/icon_titanium.png", titanium, income, TITANIUM_COLOR);
    }

    public void renderPlants(Graphics g, int x, int y, boolean income) {
        renderField(g, x, y, "images/icon_plant.png", plants, income, PLANT_COLOR);
    }

    public void renderEnergy(Graphics g, int x, int y, boolean income) {
        renderField(g, x, y, "images/icon_energy.png", energy, income, ENERGY_COLOR);
    }

    public void renderHeat(Graphics g, int x, int y, boolean income) {
        renderField(g, x, y, "images/icon_heat.png", heat, income, HEAT_COLOR);
    }

    private static void renderField(Graphics g, int x, int y, String imagePath, int amount, boolean income, Color color) {
        if (income) {
            if (amount == 0) {
                return;
            }
            g.setColor(new Color(0xCD853F));
            g.drawRect(x + 1, y + 1, 17, 17);
        }
        g.drawImage(ImageCache.getImage(imagePath), x + 2, y + 2, null);
        g.setColor(color);
        g.drawString(Integer.toString(amount), x + 30, y + 14);
    }
}
