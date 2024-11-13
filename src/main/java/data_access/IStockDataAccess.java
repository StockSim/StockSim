package data_access;

import java.util.Map;

public interface IStockDataAccess {

    /**
     * Get the price of a stock
     *
     * @param ticker: the stock ticker of the stock
     * @return the price of the stock with the given ticker
     */
    double getStockPrice(String ticker);

    /**
     * Get the prices of all stocks
     *
     * @return a hashmap with the stock ticker as the key and the stock price as the value
     * It should contain all stocks in the database
     */
    // TODO: get stocks from a list of tickers
    Map<String, Double> getStocks();
}
