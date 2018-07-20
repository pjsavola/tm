package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddWaterAction;

// TODO: Add 3 plants, 3 microbes or 2 animals to another card
public class ImportedHydrogen extends Card {

    public ImportedHydrogen() {
        super("Imported Hydrogen", 16, new Tags().space().earth().event(), false);
    }

    @Override
    public Action getInitialAction() {
        return new ActionChain(
            new AddWaterAction()
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("3 plants, 3 microbes OR 2 animals", "1 water");
    }
}
