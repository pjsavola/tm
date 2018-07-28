package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class InvestmentLoan extends Card {

    public InvestmentLoan() {
        super("Investment Loan", 3, Tags.EARTH.combine(Tags.EVENT));
    }

    @Override
    public Resources getResourceDelta(Game game) {
        return new Resources(10);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return Resources.MONEY.negate();
    }
}
