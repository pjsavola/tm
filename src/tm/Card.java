package tm;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Collections;
import java.util.List;

import com.sun.istack.internal.Nullable;
import tm.action.Action;
import tm.effect.ScoringEffect;
import tm.requirement.Requirement;

public abstract class Card implements Comparable<Card> {

    public static final int WIDTH = 200;
    public static final int TITLE_HEIGHT = 19;
    public static final int CARD_HEIGHT = 200;
    private static final Color TEXT_COLOR = new Color(0xFFFFFF);
    private static final Color BG_COLOR = new Color(0x333333);
    public static final Font FONT = new Font("Arial", Font.BOLD, 12);
    public static final Font VP_FONT = new Font("Arial", Font.BOLD, 18);
    private final String name;
    private final int cost;
    private final Tags tags;
    @Nullable
    private final Requirement requirement;
    private final boolean effect;
    @Nullable
    private Player owner;

    protected Card(String name, int cost, Tags tags) {
        this(name, cost, tags, null, false);
    }

    protected Card(String name, int cost, Tags tags, @Nullable Requirement requirement) {
        this(name, cost, tags, requirement, false);
    }

    protected Card(String name, int cost, Tags tags, @Nullable Requirement requirement, boolean effect) {
        this.name = name;
        this.cost = cost;
        this.tags = tags;
        this.requirement = requirement;
        this.effect = effect;
    }

    public Color getBorderColor() {
        if (tags.has(Tags.Type.EVENT)) {
            return Color.RED;
        } else if (cost == 0) {
            return Color.WHITE;
        } else if (effect || !getActions().isEmpty()) {
            return Color.BLUE;
        } else {
            return new Color(0x00BB00);
        }
    }

    public void setOwner(@Nullable Player player) {
        owner = player;
    }

    @Nullable
    public Player getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public Tags getTags() {
        return tags;
    }

    @Nullable
    public Requirement getRequirement() {
        return requirement;
    }

    public int getVPs() {
        return 0;
    }

    public List<Action> getActions() {
        return Collections.emptyList();
    }

    public Resources getResourceDelta(Game game) {
        return Resources.EMPTY;
    }

    public Resources getIncomeDelta(Game game) {
        return Resources.EMPTY;
    }

    @Nullable
    public Action getInitialAction(Game game) {
        return null;
    }

    public void renderTitle(Graphics g, int x, int y) {
        g.setFont(FONT);
        final FontMetrics metrics = g.getFontMetrics();
        final int h = metrics.getHeight();
        final int w = metrics.stringWidth(name);

        // Draw background
        g.setColor(BG_COLOR);
        g.fillRect(x, y, WIDTH, TITLE_HEIGHT);

        // Draw border
        g.setColor(getBorderColor());
        g.drawRect(x, y, WIDTH, TITLE_HEIGHT);

        // Draw text
        g.setColor(TEXT_COLOR);
        g.drawString(name, x + (WIDTH - w) / 2 - 8, y + 14);

        // Draw cost
        if (cost > 0) {
            final String costString = Integer.toString(cost);
            final int costWidth = metrics.stringWidth(costString);
            g.setColor(new Color(0xCCAA00));
            g.fillRect(x + 1, y + 1, 17, 17);
            g.setColor(Color.BLACK);
            g.drawString(costString, x + (19 - costWidth) / 2, y + 14);
        }

        // Draw tags
        tags.render(g, x + WIDTH, y);
    }

    // x: top-left corner
    // y: top-right corner
    public void renderContent(Graphics g, int x, int y, Game game) {
        // Draw background
        g.setColor(BG_COLOR);
        g.fillRect(x, y, WIDTH, CARD_HEIGHT);

        // Draw border
        g.setColor(getBorderColor());
        g.drawRect(x, y, WIDTH, CARD_HEIGHT);

        // Draw requirements
        if (requirement != null) {
            requirement.render(g, x, y + TITLE_HEIGHT);
        }

        // Draw resources and income
        int offset = getResourceDelta(game).render(g, x, y + TITLE_HEIGHT + 20, false);
        getIncomeDelta(game).render(g, x, y + TITLE_HEIGHT + 20 + offset, true);

        // Draw requirements
        g.setColor(Color.RED);
        int i = 0;
        for (String requirement : getRequirements()) {
            g.drawString(requirement, x + 4, y + 16 + i * 16);
            i++;
        }
        i++;

        // Draw content
        g.setColor(TEXT_COLOR);
        for (String content : getContents()) {
            g.drawString(content, x + 4, y + 16 + i * 16);
            i++;
        }

        // Draw VPS
        final int vp = getVPs();
        if (vp > 0 || this instanceof ScoringEffect) {
            g.setColor(new Color(0x8B4513));
            g.fillOval(x + WIDTH - 28, y + CARD_HEIGHT - 28, 24, 24);
            if (vp > 0) {
                g.setFont(VP_FONT);
                g.setColor(Color.BLACK);
                final String vpString = Integer.toString(getVPs());
                final int vpWidth = g.getFontMetrics().stringWidth(vpString);
                g.drawString(vpString, x + WIDTH - 28 + (24 - vpWidth) / 2, y + CARD_HEIGHT - 9);
            } else {
                ((ScoringEffect) this).render(g, x + WIDTH - 24, y + CARD_HEIGHT - 24);
            }
        }
    }

    protected List<String> getRequirements() {
        return Collections.emptyList();
    }

    protected List<String> getContents() {
        return Collections.emptyList();
    }

    @Override
    public int compareTo(Card card) {
        return name.compareTo(card.name);
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object other) {
        return name.equals(other);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
