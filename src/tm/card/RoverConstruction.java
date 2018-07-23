package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Tags;

public class RoverConstruction extends Card {

    public RoverConstruction() {
        super("Rover Construction", 18, Tags.BUILDING, true);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Effect:", "2 money for each city");
    }
}
