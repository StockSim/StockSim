package entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * A class representing a user's portfolio
 */
// TODO: update regularly
public class Portfolio {

    private final Map<String, UserStock> stocks;

    public Portfolio() {
        this.stocks = new HashMap<>();
    }

    /**
     * Create a portfolio with the given stocks
     *
     * @param stocks the stocks in the portfolio
     */
    public Portfolio(Map<String, UserStock> stocks) {
        this.stocks = new HashMap<>(stocks);
    }

    /**
     * Get the value of all stocks in the portfolio
     *
     * @return the value of all stocks in the portfolio
     */
    public double getTotalValue() {
        double result = 0.0;

        for (UserStock stock : stocks.values()) {
            result += stock.getCurrentMarketValue();
        }

        return result;
    }

    public Optional<UserStock> getUserStock(String ticker) {
        return Optional.ofNullable(stocks.get(ticker));
    }

    public void addStock(UserStock userStock) {
        stocks.put(userStock.getStock().getTicker(), userStock);
    }

    public void removeStock(UserStock userStock) {
        stocks.remove(userStock.getStock().getTicker());
    }
}
