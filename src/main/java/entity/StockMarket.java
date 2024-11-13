package entity;

import data_access.IStockDataAccess;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A singleton class representing the stock market
 */
// TODO: lock the stock market when updating stock prices
public class StockMarket {

    // thread-safe Singleton instance
    private static volatile StockMarket instance = null;

    private final Map<String, Stock> stocks = new ConcurrentHashMap<>();
    private IStockDataAccess dataAccess;
    private boolean initialized = false;

    private StockMarket() {
    }

    public static StockMarket Instance() {
        if (instance == null) {
            synchronized (StockMarket.class) {
                if (instance == null) {
                    instance = new StockMarket();
                }
            }
        }
        return instance;
    }

    // initialize the stock market with data access object
    public synchronized void initialize(IStockDataAccess dataAccess) {
        if (this.initialized) {
            throw new IllegalStateException("StockMarket is already initialized.");
        }
        this.dataAccess = dataAccess;
        this.initialized = true;
    }

    public Optional<Stock> getStock(String ticker) {
        return Optional.ofNullable(stocks.get(ticker));
    }

    /**
     * Update the stock prices in the stock market
     */
    // TODO: set interval for updating stock prices
    public void updateStockPrices() {
        if (dataAccess == null) {
            throw new IllegalStateException("StockMarket has not been initialized with a data access object.");
        }
        Map<String, Double> prices = dataAccess.getStocks();
        for (Map.Entry<String, Double> entry : prices.entrySet()) {
            String ticker = entry.getKey();
            double price = entry.getValue();
            // create a new map entry if stock does not exist, and then update price
            stocks.computeIfAbsent(ticker, k -> new Stock(ticker, price)).updatePrice(price);
        }
    }
}
