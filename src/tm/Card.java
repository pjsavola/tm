package tm;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Collections;
import java.util.List;

import com.sun.istack.internal.Nullable;
import tm.action.Action;
import tm.action.CardAction;
import tm.effect.ScoringEffect;
import tm.requirement.Requirement;

public abstract class Card implements Comparable<Card>, Selectable {

    public static final int WIDTH = 200;
    public static final int TITLE_HEIGHT = 19;
    public static final int CARD_HEIGHT = 200;
    public static final Color TEXT_COLOR = new Color(0xFFFFFF);
    public static final Color BG_COLOR = new Color(0x333333);
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

    public List<CardAction> getActions() {
        return Collections.emptyList();
    }

    public Resources getResourceDelta() {
        return Resources.EMPTY;
    }

    public Resources getIncomeDelta() {
        return Resources.EMPTY;
    }

    @Nullable
    public Action getInitialAction(Game game) {
        return null;
    }

    @Override
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
        tags.render(g, x + WIDTH, y + 1, false);
    }

    // x: top-left corner
    // y: top-right corner
    @Override
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
        int currentY = y + TITLE_HEIGHT + 20;
        currentY = getResourceDelta().render(g, x + 5, currentY, false).y + 4;
        getIncomeDelta().render(g, x + 5, currentY, true);

        // Draw requirements
        g.setColor(Color.RED);
        int i = 0;
        for (String requirement : getRequirements()) {
            g.drawString(requirement, x + 4, y + 16 + i * 16);
            i++;
        }
        i++;

        // Draw content
        /*
        g.setColor(TEXT_COLOR);
        for (String content : getContents()) {
            g.drawString(content, x + 4, y + 16 + i * 16);
            i++;
        }*/

        // Draw effect
        int actionOffset = 0;
        if (effect || this instanceof ScoringEffect) {
            final int px = x + WIDTH / 2;
            final int py = y + TITLE_HEIGHT + 35;
            g.setColor(Color.WHITE);
            g.drawString("Effect:", px, py);
            renderEffect(g, px, py + 5);
            actionOffset += 60;
        }

        // Draw action
        for (Action action : getActions()) {
            final int px = x + WIDTH / 2;
            final int py = y + TITLE_HEIGHT + 35 + actionOffset;
            g.setColor(Color.WHITE);
            g.drawString("Action:", px, py);
            action.render(g, px, py + 5, game);
            actionOffset += 60;
        }

        // Draw initial action
        final Action action = getInitialAction(game);
        if (action != null) {
            action.render(g, x + 10, y + CARD_HEIGHT - 55, game);
        }

        // Draw markers
        if (this instanceof CardWithMarkers) {
            final CardWithMarkers cardWithMarkers = (CardWithMarkers) this;
            final int middleX = x + WIDTH / 2;
            final int middleY = y + CARD_HEIGHT - 26;
            Renderer.renderMarker(g, middleX - 6, middleY - 6);
            Renderer.renderText(g, Integer.toString(cardWithMarkers.getMarkerCount()), middleX, middleY, true);
            final String ratio = cardWithMarkers.getRatio();
            if (ratio != null) {
                g.setColor(Color.LIGHT_GRAY);
                Renderer.renderText(g, ratio, middleX, middleY + 14, true);
            }
        }

        // Draw VPS
        final int vp = getVPs();
        if (vp > 0) {
            Renderer.renderVPCircle(g, x + WIDTH - 28, y + CARD_HEIGHT - 28);
            g.setFont(VP_FONT);
            g.setColor(Color.BLACK);
            Renderer.renderText(g, Integer.toString(vp), x + WIDTH - 16, y + CARD_HEIGHT - 16, true);
        }
    }

    protected void renderEffect(Graphics g, int x, int y) {
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
    public boolean equals(Object other) {
        if (other instanceof Card) {
            return name.equals(((Card) other).name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
