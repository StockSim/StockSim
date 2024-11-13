package view.view_events;

import entity.Portfolio;

import java.util.EnumSet;

public class UpdateAssetEvent extends ViewEvent {
    private final Portfolio portfolio;
    private final double balance;

    /**
     * Constructs an UpdatePortfolioEvent with a specified portfolio and balance,
     * using only the UPDATE_PORTFOLIO event type.
     *
     * @param portfolio The portfolio to update.
     * @param balance   The balance associated with the portfolio.
     */
    public UpdateAssetEvent(Portfolio portfolio, double balance) {
        super(EnumSet.of(EventType.UPDATE_ASSET));
        this.portfolio = portfolio;
        this.balance = balance;
    }

    /**
     * Constructs an UpdatePortfolioEvent with a specified portfolio, balance, and a set of event types.
     *
     * @param portfolio The portfolio to update.
     * @param balance   The balance associated with the portfolio.
     * @param types     The set of event types for this event.
     */
    public UpdateAssetEvent(Portfolio portfolio, double balance, EnumSet<EventType> types) {
        super(types);
        this.portfolio = portfolio;
        this.balance = balance;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public double getBalance() {
        return balance;
    }
}