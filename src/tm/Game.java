package tm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.istack.internal.Nullable;
import tm.card.AdaptedLichen;
import tm.card.Algae;
import tm.card.Ants;
import tm.card.Archaebacteria;
import tm.card.ArcticAlgae;
import tm.card.Asteroid;
import tm.card.AsteroidMining;
import tm.card.AsteroidMiningConsortium;
import tm.card.BigAsteroid;
import tm.card.BlackPolarDust;
import tm.card.Capital;
import tm.card.CarbonateProcessing;
import tm.card.CloudSeeding;
import tm.card.ColonizerTrainingCamp;
import tm.card.Comet;
import tm.card.CupolaCity;
import tm.card.DeepWellHeating;
import tm.card.DeimosDown;
import tm.card.DevelopmentCenter;
import tm.card.DomedCrater;
import tm.card.EosChasmaNationalPark;
import tm.card.EquatorialMagnetizer;
import tm.card.Fish;
import tm.card.FoodFactory;
import tm.card.GHGProducingBacteria;
import tm.card.ImportedHydrogen;
import tm.card.InterstellarColonyShip;
import tm.card.InventorsGuild;
import tm.card.KelpFarming;
import tm.card.LakeMarineris;
import tm.card.LightningHarvest;
import tm.card.LunarBeam;
import tm.card.MartianRail;
import tm.card.MethaneFromTitan;
import tm.card.MirandaResort;
import tm.card.NaturalPreserve;
import tm.card.NitrogenRichAsteroid;
import tm.card.NoctisCity;
import tm.card.NuclearPower;
import tm.card.OptimalAerobraking;
import tm.card.PhobosSpaceHaven;
import tm.card.Predators;
import tm.card.RegolithEaters;
import tm.card.ReleaseOfInertGases;
import tm.card.ResearchOutpost;
import tm.card.RoverConstruction;
import tm.card.SearchForLife;
import tm.card.SecurityFleet;
import tm.card.SmallAnimals;
import tm.card.SpaceElevator;
import tm.card.SpaceStation;
import tm.card.Tardigrades;
import tm.card.UndergroundCity;
import tm.card.Virus;
import tm.card.WaterImportFromEuropa;
import tm.corporation.Credicor;
import tm.corporation.Ecoline;
import tm.corporation.Helion;
import tm.corporation.InterplanetaryCinematics;
import tm.corporation.Inventrix;
import tm.corporation.MiningGuild;
import tm.corporation.Phoblog;
import tm.corporation.SaturnSystems;
import tm.corporation.Teractor;
import tm.corporation.TharsisRepublic;
import tm.corporation.Thorgate;
import tm.corporation.UnitedNationsMarsInitiative;

public class Game extends JPanel {

    private static final long serialVersionUID = 1L;
    private final Map<Point, Tile> grid = new HashMap<>();
    private final ActionHandler actionHandler;
    private Player currentPlayer = new Player();
    private Planet planet = new Planet();
    private Deque<Card> deck = new ArrayDeque<>();
    private List<Card> discard = new ArrayList<>();
    private Deque<Card> corporationDeck = new ArrayDeque<>();
    private static final Random r = new Random();

    public Game() {
        setPreferredSize(new Dimension(700, 700));

        for (int i = 0; i <= 4; i++) new Tile(i, -4, grid);
        for (int i = -1; i <= 4; i++) new Tile(i, -3, grid);
        for (int i = -2; i <= 4; i++) new Tile(i, -2, grid);
        for (int i = -3; i <= 4; i++) new Tile(i, -1, grid);
        for (int i = -4; i <= 4; i++) new Tile(i, 0, grid);
        for (int i = -4; i <= 3; i++) new Tile(i, 1, grid);
        for (int i = -4; i <= 2; i++) new Tile(i, 2, grid);
        for (int i = -4; i <= 1; i++) new Tile(i, 3, grid);
        for (int i = -4; i <= 0; i++) new Tile(i, 4, grid);

        deck.add(new Archaebacteria());
        deck.add(new CarbonateProcessing());
        deck.add(new NaturalPreserve());
        deck.add(new NuclearPower());
        deck.add(new LightningHarvest());
        deck.add(new Algae());
        deck.add(new AdaptedLichen());
        deck.add(new Tardigrades());
        deck.add(new Virus());
        deck.add(new MirandaResort());
        deck.add(new Fish());
        deck.add(new LakeMarineris());
        deck.add(new SmallAnimals());
        deck.add(new KelpFarming());
        ///
        deck.add(new ColonizerTrainingCamp());
        deck.add(new AsteroidMiningConsortium());
        deck.add(new DeepWellHeating());
        deck.add(new CloudSeeding());
        deck.add(new SearchForLife());
        deck.add(new InventorsGuild());
        deck.add(new MartianRail());
        deck.add(new Capital());
        deck.add(new Asteroid());
        deck.add(new Comet());
        deck.add(new BigAsteroid());
        deck.add(new WaterImportFromEuropa());
        deck.add(new SpaceElevator());
        deck.add(new DevelopmentCenter());
        deck.add(new EquatorialMagnetizer());
        deck.add(new DomedCrater());
        deck.add(new NoctisCity());
        deck.add(new MethaneFromTitan());
        deck.add(new ImportedHydrogen());
        deck.add(new ResearchOutpost());
        deck.add(new PhobosSpaceHaven());
        deck.add(new BlackPolarDust());
        deck.add(new ArcticAlgae());
        deck.add(new Predators());
        deck.add(new SpaceStation());
        deck.add(new EosChasmaNationalPark());
        deck.add(new InterstellarColonyShip());
        deck.add(new SecurityFleet());
        deck.add(new CupolaCity());
        deck.add(new LunarBeam());
        deck.add(new OptimalAerobraking());
        deck.add(new UndergroundCity());
        deck.add(new RegolithEaters());
        deck.add(new GHGProducingBacteria());
        deck.add(new Ants());
        deck.add(new ReleaseOfInertGases());
        deck.add(new NitrogenRichAsteroid());
        deck.add(new RoverConstruction());
        deck.add(new DeimosDown());
        deck.add(new AsteroidMining());
        deck.add(new FoodFactory());

        corporationDeck.add(new Credicor());
        corporationDeck.add(new Ecoline());
        corporationDeck.add(new Helion());
        corporationDeck.add(new InterplanetaryCinematics());
        corporationDeck.add(new Inventrix());
        corporationDeck.add(new MiningGuild());
        corporationDeck.add(new Phoblog());
        corporationDeck.add(new SaturnSystems());
        corporationDeck.add(new TharsisRepublic());
        corporationDeck.add(new Thorgate());
        corporationDeck.add(new Teractor());
        corporationDeck.add(new UnitedNationsMarsInitiative());

        // Initial tiles
        placeInitialTiles();

        actionHandler = new ActionHandler(this);
    }

    public Tile getClosestTile(final int x, final int y) {
        double minDistance = Double.POSITIVE_INFINITY;
        Tile closestTile = null;
        final Point clickPoint = new Point(x, y);
        for (final Tile tile : grid.values()) {
            final Point tilePoint = new Point(tile.getX(347, 43.5), tile.getY(350, 43.5));
            final double distance = tilePoint.distance(clickPoint);
            if (distance < minDistance && distance < 37.5 * 0.8) {
                minDistance = distance;
                closestTile = tile;
            }
        }
        return closestTile;
    }

    public Tile getNoctisTile() {
        final Optional<Tile> noctis = grid.values().stream().filter(tile -> tile.getProperties() != null && tile.getProperties().isNoctis()).findAny();
        if (noctis.isPresent()) {
            return noctis.get();
        }
        throw new RuntimeException("Place for Noctis City not found!");
    }

    @Override
    public void paintComponent(Graphics g) {
        final BufferedImage image = ImageCache.getImage("images/mars.png");
        g.drawImage(image, 0, 0, null);
        grid.values().forEach(tile -> tile.draw(g));
        actionHandler.render(g);
        currentPlayer.render(g);
        planet.render(g);

        g.drawImage(ImageCache.getImage("images/icon_city.png"), 650, 90, null);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString(Long.toString(getCityCount()), 680, 110);
    }

    public int getCityCount() {
        return (int) grid.values().stream().filter(Tile::isCity).count();
    }

    public ActionHandler getActionHandler() {
        return actionHandler;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Nullable
    public Player getPlayer(Class<? extends Corporation> corporation) {
        if (currentPlayer.getCorporation().getClass().equals(corporation)) {
            return currentPlayer;
        } else {
            return null;
        }
    }

    public Planet getPlanet() {
        return planet;
    }

    @Nullable
    public Card drawCard() {
        if (deck.isEmpty()) {
            // Shuffle the discard to deck
            while (!discard.isEmpty()) {
                deck.push(discard.remove(r.nextInt(discard.size())));
            }
        }
        if (deck.isEmpty()) {
            // Run out of cards
            return null;
        }
        return deck.pop();
    }

    public boolean canDrawCard() {
        return !deck.isEmpty() || !discard.isEmpty();
    }

    public Deque<Card> getCorporationDeck() {
        return corporationDeck;
    }

    public List<Card> getDiscardDeck() {
        return discard;
    }

    // This is different from game rules, due to not needing card costs for randomization.
    private void placeInitialTiles() {
        int cityCount = 2;
        final Tile[] tiles = grid.values().toArray(new Tile[grid.values().size()]);
        while (cityCount > 0) {
            final Tile randomTile = tiles[r.nextInt(tiles.length)];
            if (isSuitable(randomTile)) {
                cityCount--;
                randomTile.setType(Tile.Type.CITY);
                final List<Tile> neighbors = randomTile.getNeighbors();
                while (true) {
                    final Tile randomNeighbor = neighbors.get(r.nextInt(neighbors.size()));
                    if (isSuitable(randomNeighbor)) {
                        randomNeighbor.setType(Tile.Type.GREENERY);
                        break;
                    }
                }
            }
        }
    }

    private static boolean isSuitable(Tile tile) {
        if (tile.getType() != null) {
            return false;
        }
        final TileProperties properties = tile.getProperties();
        return properties == null || (!properties.isWater() && !properties.isNoctis());
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        Game g = new Game();
        g.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final int x = e.getX();
                final int y = e.getY();
                if (Math.abs(x - 10) <= 8 && Math.abs(y - 10) <= 8) {
                    g.getActionHandler().process(ActionType.HEAT_TO_MONEY);
                } else if (Math.abs(x - 10) <= 8 && Math.abs(y - 28) <= 8) {
                    g.getActionHandler().adjustPayment(true, e.getButton() == MouseEvent.BUTTON1);
                } else if (Math.abs(x - 10) <= 8 && Math.abs(y - 46) <= 8) {
                    g.getActionHandler().adjustPayment(false, e.getButton() != MouseEvent.BUTTON1);
                } else if (Math.abs(x - 10) <= 8 && Math.abs(y - 64) <= 8) {
                    g.getActionHandler().process(ActionType.PLANT_TO_GREENERY);
                } else if (Math.abs(x - 10) <= 8 && Math.abs(y - 82) <= 8) {
                    g.getActionHandler().process(ActionType.ENERGY);
                } else if (Math.abs(x - 10) <= 8 && Math.abs(y - 100) <= 8) {
                    g.getActionHandler().process(ActionType.HEAT_TO_TEMPERATURE);
                } else if (Math.abs(x - 10) <= 8 && Math.abs(y - 118) <= 8) {
                    g.getActionHandler().process(ActionType.TR);
                } else if (Math.abs(x - 663) <= 5 && Math.abs(y - 17) <= 15) {
                    g.getActionHandler().process(ActionType.TEMPERATURE);
                } else if (Math.abs(x - 663) <= 13 && Math.abs(y - 47) <= 13) {
                    g.getActionHandler().process(ActionType.GREENERY);
                } else if (Math.abs(x - 663) <= 13 && Math.abs(y - 73) <= 13) {
                    g.getActionHandler().process(ActionType.WATER);
                } else if (Math.abs(x - 663) <= 13 && Math.abs(y - 103) <= 13) {
                    g.getActionHandler().process(ActionType.CITY);
                } else if (Math.abs(x - 10) <= 8 && Math.abs(y - 154) <= 8) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        g.getActionHandler().process(ActionType.PLAY);
                    } else {
                        g.getActionHandler().process(ActionType.DISCARD);
                    }
                } else if (Math.abs(x - 10) <= 8 && Math.abs(y - 172) <= 8) {
                    g.getActionHandler().process(ActionType.CUSTOM);
                } else if (Math.abs(x - 350) <= 60 && Math.abs(y - 688) <= 11) {
                    g.getActionHandler().process(ActionType.PASS);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        f.addKeyListener(g.getActionHandler().getKeyListener());
        f.setTitle("Terraforming Mars");
        f.setContentPane(g);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
