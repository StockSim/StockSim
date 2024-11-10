package api;

import java.util.Map;

public interface IStockDataAccess {

    /**
     * Get the prices of all stocks
     * @return a hashmap with the stock ticker as the key and a tuple of the stock price and floating stock as the value.
     * It should contain all stocks in the database.
     */
    Map<String, Double> getStockData();
}
