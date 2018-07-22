package tm.card;

import tm.Card;
import tm.Tags;

// Removal of 2 animals or 5 plants is done from dummy player
public class Virus extends Card {

    public Virus() {
        super("Virus", 1, new Tags().microbe().event());
    }
}
