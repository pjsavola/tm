package tm;

public class Resources {
	int money;
	int aluminum;
	int titanium;
	int plants;
	int energy;
	int heat;
	
	public Resources(int money) {
		this.money = money;
	}
	
	public Resources(int money, int aluminum, int titanium, int plants, int energy, int heat) {
		this.money = money;
		this.aluminum = aluminum;
		this.titanium = titanium;
		this.plants = plants;
		this.energy = energy;
		this.heat = heat;
	}
	
	public void adjust(final Resources delta) {
		money += delta.money;
		aluminum += delta.aluminum;
		titanium += delta.titanium;
		plants += delta.plants;
		energy += delta.energy;
		heat += delta.heat;
	}
	
	public boolean canAdjust(final Resources delta) {
		return
			money + delta.money >= 0 &&
			aluminum + delta.aluminum >= 0 &&
			titanium + delta.titanium >= 0 &&
			plants + delta.plants >= 0 &&
			energy + delta.energy >= 0 &&
			heat + delta.heat >= 0;
	}
	
	public Resources negate() {
		return new Resources(-money, -aluminum, -titanium, -plants, -energy, -heat);
	}
	
	public Resources combine(final Resources resources) {
		return new Resources(money + resources.money, aluminum + resources.aluminum, titanium + resources.titanium,
				             plants + resources.plants, energy + resources.energy, heat + resources.heat);
	}
}
