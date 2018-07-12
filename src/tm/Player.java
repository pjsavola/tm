package tm;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddOxygenAction;
import tm.action.IncomeDeltaAction;
import tm.action.PlaceTileAction;
import tm.action.ResourceDeltaAction;
import tm.completable.Completable;
import tm.completable.InstantCompletable;
import tm.corporation.Credicor;
import tm.corporation.Ecoline;
import tm.corporation.Helion;
import tm.corporation.Phoblog;
import tm.corporation.Teractor;
import tm.corporation.Thorgate;
import tm.corporation.UnitedNationsMarsInitiative;

public class Player {
	private static final Font font = new Font("Arial", Font.BOLD, 12);
	private Resources resources = new Resources(0);
	private Resources income = new Resources(0);
	private int rating = 20;
	private int savedRating = 20;
	private final Color color = new Color(0xFF0000);
	final Set<Tile> ownedTiles = new HashSet<>();
	final List<Card> cards = new ArrayList<>();
	Corporation corporation;

	public Color getColor() {
		return color;
	}
	
	public void adjustResources(Resources delta) {
		resources.adjust(delta);
	}
	
	public boolean canAdjustResources(Resources delta) {
		return resources.canAdjust(delta);
	}

	public boolean canAdjustIncome(Resources delta) {
	    final Resources newIncome = income.combine(delta);
	    return newIncome.steel >= 0 && newIncome.titanium >= 0 && newIncome.plants >= 0 && newIncome.energy >= 0 && newIncome.heat >= 0;
	}

	public void adjustRating(int delta) {
		rating += delta;
	}

	public void saveRating() {
	    savedRating = rating;
    }

    private boolean hasIncreasedRating() {
	    return rating > savedRating;
    }
	
	public void adjustIncome(Resources delta) {
		income.adjust(delta);
	}

	public void setCorporation(Corporation corporation) {
		this.corporation = corporation;
	}

	public Corporation getCorporation() {
	    return corporation;
    }

	public List<Card> getCards() {
		return cards;
	}

	public Resources getIncome() {
		final int leftOverEnergy = resources.energy;
		return income.combine(new Resources(rating, 0, 0, 0, -leftOverEnergy, leftOverEnergy));
	}

	public int getSteel() {
		return resources.steel;
	}

	public int getTitanium() {
		return resources.titanium;
	}

	public int getSteelValue() {
	    return 2;
    }

    public int getTitaniumValue() {
	    if (corporation instanceof Phoblog) {
	        return 4;
        }
	    return 3;
    }

    public int getDiscount(Card card) {
	    if (card.getTags().hasPower() && corporation instanceof Thorgate) {
            return 3;
        }
        if (card.getTags().hasEvent() && corporation instanceof Teractor) {
	        return 3;
        }
	    return 0;
    }

    public boolean fulfillsRequirements(Card card, Planet planet) {
	    return true;
    }

    public List<Action> getActions() {
        final List<Action> actions = new ArrayList<>();
        if (corporation instanceof Credicor) {
            actions.add(new ActionChain('g', "Greenery",
                new ResourceDeltaAction(new Resources(-23)),
                new PlaceTileAction(Tile.Type.GREENERY),
                new AddOxygenAction(),
                new ResourceDeltaAction(new Resources(4))
            ));
            actions.add(new ActionChain('c', "City",
                new ResourceDeltaAction(new Resources(-25)),
                new PlaceTileAction(Tile.Type.CITY),
                new IncomeDeltaAction(new Resources(1)),
                new ResourceDeltaAction(new Resources(4))
            ));
        } else if (corporation instanceof Ecoline) {
            actions.add(new ActionChain('p', "Plant",
                new ResourceDeltaAction(new Resources(0, 0, 0, -7, 0, 0)),
                new PlaceTileAction(Tile.Type.GREENERY),
                new AddOxygenAction()
            ));
        } else if (corporation instanceof Helion) {
            actions.add(new ActionChain('m', "Heat to money",
                new ResourceDeltaAction(new Resources(1, 0, 0, 0, 0, -1))
            ));
        } else if (corporation instanceof Thorgate) {
            actions.add(new ActionChain('e', "Energy income",
                new ResourceDeltaAction(new Resources(-8)),
                new IncomeDeltaAction(new Resources(0, 0, 0, 0, 1, 0))
            ));
        } else if (corporation instanceof UnitedNationsMarsInitiative) {
            actions.add(new ActionChain('t', "Increase TR",
                new ResourceDeltaAction(new Resources(-3)),
                new Action() {
                    @Override
                    public boolean check(Game game) {
                        return hasIncreasedRating();
                    }

                    @Override
                    public Completable begin(Game game) {
                        return new InstantCompletable(game) {
                            @Override
                            public void complete() {
                                game.getCurrentPlayer().adjustRating(1);
                            }

                            @Override
                            public void undo() {
                                game.getCurrentPlayer().adjustRating(-1);
                            }

                            @Override
                            public void redo() {
                                game.getCurrentPlayer().adjustRating(1);
                            }
                        };
                    }
                }
            ));
        }
        return actions;
    }

	public int getPoints() {
		final AtomicLong total = new AtomicLong(rating);
		ownedTiles
			.stream()
			.filter(tile -> tile.getType() == Tile.Type.CITY)
			.forEach(tile -> {
				total.addAndGet(tile
					.getNeighbors()
					.stream()
					.filter(neighborTile -> neighborTile.getType() == Tile.Type.GREENERY)
					.count());
			});
		total.addAndGet(ownedTiles
			.stream()
			.filter(tile -> tile.getType() == Tile.Type.GREENERY)
			.count());
		return total.intValue();
	}
	
    public Set<Tile> getFreeAdjacentTiles() {
    	final Set<Tile> freeAdjacentTiles = new HashSet<>();
    	ownedTiles.forEach(tile -> tile
    		.getNeighbors()
    	    .stream()
    	    .filter(neighborTile -> neighborTile.getOwner() == null)
    	    .forEach(freeAdjacentTiles::add));
    	return freeAdjacentTiles; 
    }
	
	public void render(Graphics g) {
		final Color oldColor = g.getColor();
		g.setFont(font);
		renderText(g, "Money", resources.money, income.money, 1, 0xFFFF00);
		renderText(g, "Steel", resources.steel, income.steel, 2, 0x8B4513);
		renderText(g, "Titanium", resources.titanium, income.titanium, 3, 0x888888);
		renderText(g, "Plants", resources.plants, income.plants, 4, 0x00FF00);
		renderText(g, "Energy", resources.energy, income.energy, 5, 0x6600FF);
		renderText(g, "Heat", resources.heat, income.heat, 6, 0xFF9900);
		renderText(g, "Rating", rating, 0, 7, 0x0000FF);
		renderText(g, "Points", getPoints(), 0, 8, 0x00FFFF);
		renderText(g, "Cards", getCards().size(), 0, 9, 0xCCCCCC);
		if (corporation != null) {
			final String name = corporation.getName();
			final int w = g.getFontMetrics().stringWidth(name);
			g.setColor(new Color(0xFFFFFF));
			g.drawString(corporation.getName(), 350 - w / 2, 12);
		}
        g.setColor(oldColor);
	}
	
	private static void renderText(Graphics g, String name, int amount, int income, int i, int color) {
		g.setColor(new Color(color));
		final String text = name + ": " + amount + (income > 0 ? " (" + income + ")" : "");
		final FontMetrics metrics = g.getFontMetrics(); 
        int h = metrics.getHeight();
        g.drawString(text, 2, (h + 1) * i);
	}
}
