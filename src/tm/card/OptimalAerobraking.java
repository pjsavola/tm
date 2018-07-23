package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Tags;

public class OptimalAerobraking extends Card {

    public OptimalAerobraking() {
        super("Optimal Aerobraking", 7, Tags.SPACE, true);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Effect:", "Gain 3 money and 3 heat for each space event");
    }
}
