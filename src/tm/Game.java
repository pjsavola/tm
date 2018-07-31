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
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.sun.istack.internal.Nullable;
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
    private ActionHandler actionHandler;
    private Player currentPlayer = new Player();
    private Planet planet = new Planet();
    private Deque<Card> deck = new ArrayDeque<>();
    private List<Card> discard = new ArrayList<>();
    private Set<Card> corporationDeck = new TreeSet<>();
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

        deck = Cards.buildDeck();

        ///

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
        g.drawString(Long.toString(getCityCount(false)), 680, 110);
    }

    public int getCityCount(boolean onMarsOnly) {
        int cityCount = (int) grid.values().stream().filter(Tile::isCity).count();
        if (onMarsOnly) {
            return cityCount;
        }
        if (Cards.PHOBOS_SPACE_HAVEN.getOwner() != null) {
            cityCount++;
        }
        if (Cards.GANYMEDE_COLONY.getOwner() != null) {
            cityCount++;
        }
        return cityCount;
    }

    public ActionHandler getActionHandler() {
        return actionHandler;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
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

    public Set<Card> getCorporationDeck() {
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

    public static class Test extends JPanel {
        public Card card;

        @Override
        public void paintComponent(Graphics g) {
            if (card != null) {
                card.renderTitle(g, 0, 0);
                card.renderContent(g, 0, 22, null);
            }
        }
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
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        g.getActionHandler().start();
    }
}
