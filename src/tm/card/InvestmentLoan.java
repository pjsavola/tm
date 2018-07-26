package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.action.ResourceDeltaAction;

public class InvestmentLoan extends Card {

    public InvestmentLoan() {
        super("Investment Loan", 3, Tags.EARTH.combine(Tags.EVENT));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new IncomeDeltaAction(Resources.MONEY.negate()),
            new ResourceDeltaAction(new Resources(10))
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("10 money", "-1 money income");
    }
}
