package view.view_event;

import entity.Portfolio;

public class UpdatePortfolioEvent extends ViewEvent {

    public final double balance;
    public final Portfolio portfolio;

    public UpdatePortfolioEvent(Portfolio portfolio, double balance) {
        this.portfolio = portfolio;
        this.balance = balance;
    }

}