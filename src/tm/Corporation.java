package tm;

import java.awt.Color;

public abstract class Corporation extends Card {

    private static final Color BORDER_COLOR = new Color(0x999999);

    protected Corporation(String name, Tags tags) {
        super(name, 0, tags);
    }

    @Override
    public Color getBorderColor() {
        return BORDER_COLOR;
    }
}
