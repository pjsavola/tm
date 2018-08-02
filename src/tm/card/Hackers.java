package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;

// Removes 2 money income from dummy opponent
public class Hackers extends Card {

    public Hackers() {
        super("Hackers", 3, Tags.EMPTY);
    }

    @Override
    public int getVPs() {
        return -1;
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(2, 0, 0, 0, -1, 0);
    }
}
