package tm;

import java.awt.Color;
import java.awt.Font;
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
	private Resources resourcesDelta = new Resources(0);
    private Resources incomeDelta = new Resources(0);
    private Tags tags = new Tags();
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

	public void setResourcesDelta(Resources delta) {
	    resourcesDelta = delta;
    }

    public void setIncomeDelta(Resources delta) {
        incomeDelta = delta;
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

    public void addTags(Tags tags) {
	    this.tags.combine(tags, true);
    }

    public void removeTags(Tags tags) {
        this.tags.combine(tags, false);
    }

    public List<Action> getActions() {
        final List<Action> actions = new ArrayList<>();
        if (corporation instanceof Credicor) {
            actions.add(new ActionChain(ActionType.GREENERY, "Greenery",
                new ResourceDeltaAction(new Resources(-23)),
                new PlaceTileAction(Tile.Type.GREENERY),
                new AddOxygenAction(),
                new ResourceDeltaAction(new Resources(4))
            ));
            actions.add(new ActionChain(ActionType.CITY, "City",
                new ResourceDeltaAction(new Resources(-25)),
                new PlaceTileAction(Tile.Type.CITY),
                new IncomeDeltaAction(new Resources(1)),
                new ResourceDeltaAction(new Resources(4))
            ));
        } else if (corporation instanceof Ecoline) {
            actions.add(new ActionChain(ActionType.PLANT_TO_GREENERY, "Plant",
                new ResourceDeltaAction(new Resources(0, 0, 0, -7, 0, 0)),
                new PlaceTileAction(Tile.Type.GREENERY),
                new AddOxygenAction()
            ));
        } else if (corporation instanceof Helion) {
            actions.add(new ActionChain(ActionType.HEAT_TO_MONEY, "Heat to money",
                new ResourceDeltaAction(new Resources(1, 0, 0, 0, 0, -1))
            ));
        } else if (corporation instanceof Thorgate) {
            actions.add(new ActionChain(ActionType.ENERGY, "Energy income",
                new ResourceDeltaAction(new Resources(-8)),
                new IncomeDeltaAction(new Resources(0, 0, 0, 0, 1, 0))
            ));
        } else if (corporation instanceof UnitedNationsMarsInitiative) {
            actions.add(new ActionChain(ActionType.TR, "Increase TR",
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
		final Resources renderableResources = resources.combine(resourcesDelta);
        final Resources renderableIncome = income.combine(incomeDelta);
		renderText(g, "images/icon_money.png", renderableResources.money, renderableIncome.money, 1, 0xFFFF00);
		renderText(g, "images/icon_steel.png", renderableResources.steel, renderableIncome.steel, 2, 0x8B4513);
		renderText(g, "images/icon_titanium.png", renderableResources.titanium, renderableIncome.titanium, 3, 0x888888);
		renderText(g, "images/icon_plant.png", renderableResources.plants, renderableIncome.plants, 4, 0x00FF00);
		renderText(g, "images/icon_energy.png", renderableResources.energy, renderableIncome.energy, 5, 0x6600FF);
		renderText(g, "images/icon_heat.png", renderableResources.heat, renderableIncome.heat, 6, 0xFF9900);
		renderText(g, "images/icon_tr.png", rating, 0, 7, 0x0000FF);
		renderText(g, "images/icon_vp.png", getPoints(), 0, 8, 0x00FFFF);
		renderText(g, "images/icon_card.png", getCards().size(), 0, 9, 0xCCCCCC);

		tags.renderVertical(g, 2, 682);

        if (corporation != null) {
			final String name = corporation.getName();
			final int w = g.getFontMetrics().stringWidth(name);
			g.setColor(new Color(0xFFFFFF));
			g.drawString(corporation.getName(), 350 - w / 2, 12);
		}
        g.setColor(oldColor);
	}
	
	private static void renderText(Graphics g, String path, int amount, int income, int i, int color) {
	    g.drawImage(ImageCache.getImage(path), 2, i * 18 - 16, null);
		g.setColor(new Color(color));
        g.drawString(Integer.toString(amount), 30, 18 * i - 4);
        if (income > 0) {
            final String incomeString = (income > 0 ? "+" : "") + income;
            g.drawString(incomeString, 50, 18 * i - 4);
        }
	}
}
