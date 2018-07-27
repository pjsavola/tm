package tm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tags {

    public enum Type {
        EVENT("images/tag_event.png"),
        BUILDING("images/tag_building.png"),
        CITY("images/tag_city.png"),
        SPACE("images/tag_space.png"),
        EARTH("images/tag_earth.png"),
        JOVIAN("images/tag_jovian.png"),
        SCIENCE("images/tag_science.png"),
        POWER("images/tag_power.png"),
        PLANT("images/tag_plant.png"),
        ANIMAL("images/tag_animal.png"),
        MICROBE("images/tag_microbe.png");

        private final String imagePath;

        private Type(String imagePath) {
            this.imagePath = imagePath;
        }

        public Tags createTag() {
            return create(ordinal(), 1);
        }

        public Tags createTags(int count) {
            return create(ordinal(), count);
        }

        public BufferedImage getImage() {
            return ImageCache.getImage(imagePath);
        }
    };

    public static final Tags EMPTY = new Tags();
    public static final Tags EVENT = Type.EVENT.createTag();
    public static final Tags BUILDING = Type.BUILDING.createTag();
    public static final Tags CITY = Type.CITY.createTag();
    public static final Tags SPACE = Type.SPACE.createTag();
    public static final Tags EARTH = Type.EARTH.createTag();
    public static final Tags JOVIAN = Type.JOVIAN.createTag();
    public static final Tags SCIENCE = Type.SCIENCE.createTag();
    public static final Tags POWER = Type.POWER.createTag();
    public static final Tags PLANT = Type.PLANT.createTag();
    public static final Tags ANIMAL = Type.ANIMAL.createTag();
    public static final Tags MICROBE = Type.MICROBE.createTag();

    // Common combos
    public static final Tags SCIENCE_2 = Type.SCIENCE.createTags(2);
    public static final Tags SCIENCE_3 = Type.SCIENCE.createTags(3);
    public static final Tags SCIENCE_BUILDING = SCIENCE.combine(BUILDING);
    public static final Tags BUILDING_CITY = BUILDING.combine(CITY);
    public static final Tags BUILDING_POWER = BUILDING.combine(POWER);
    public static final Tags SPACE_JOVIAN = SPACE.combine(JOVIAN);
    public static final Tags SPACE_EVENT = SPACE.combine(EVENT);
    public static final Tags SPACE_EARTH_EVENT = SPACE_EVENT.combine(EARTH);

    private final int[] tagCounts = new int[Type.values().length];

    private Tags() {
    }

    private static Tags create(int index, int count) {
        final Tags result = new Tags();
        result.tagCounts[index] = count;
        return result;
    }

    public Tags combine(Tags tags) {
        final Tags result = new Tags();
        for (int i = 0; i < tagCounts.length; i++) {
            result.tagCounts[i] = tagCounts[i] + tags.tagCounts[i];
        }
        return result;
    }

    public Tags subtract(Tags tags) {
        final Tags result = new Tags();
        for (int i = 0; i < tagCounts.length; i++) {
            result.tagCounts[i] = tagCounts[i] - tags.tagCounts[i];
        }
        return result;
    }

    public boolean has(Type type) {
        return getCount(type) > 0;
    }

    public int getCount(Type type) {
        return tagCounts[type.ordinal()];
    }

    public boolean hasAll(Tags tags) {
        for (int i = 0; i < tagCounts.length; i++) {
            if (tagCounts[i] < tags.tagCounts[i]) {
                return false;
            }
        }
        return true;
    }

    // (x,y) is top right corner of the tags
    public void render(Graphics g, int x, int y) {
        int offset = x - 17;
        for (Type type : Type.values()) {
            offset = drawTags(g, type.getImage(), getCount(type), offset, y + 2);
        }
    }

    private static int drawTags(Graphics g, BufferedImage image, int count, int x, int y) {
        int actualX = x;
        for (int i = 0; i < count; i++) {
            g.drawImage(image, actualX, y, null);
            actualX -= 17;
        }
        return actualX;
    }

    // (x,y) is bottom left corner of the tags
    public void renderVertical(Graphics g, int x, int y) {
        g.setColor(Color.WHITE);
        int offset = y - 18;
        final Type[] types = Type.values();
        for (int i = types.length - 1; i >= 0; i--) {
            offset = drawTagCount(g, types[i].getImage(), getCount(types[i]), x + 2 , offset);
        }
    }

    private static int drawTagCount(Graphics g, BufferedImage image, int count, int x, int y) {
        if (count > 0) {
            g.drawImage(image, x, y, null);
            g.drawString(Integer.toString(count), x + 22, y + 12);
            return y - 18;
        } else {
            return y;
        }
    }

}
