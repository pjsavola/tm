package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;

public class InvestmentLoan extends Card {

    public InvestmentLoan() {
        super("Investment Loan", 3, Tags.EARTH.combine(Tags.EVENT));
    }

    @Override
    public Resources getResourceDelta() {
        return new Resources(10);
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.MONEY.negate();
    }
}
