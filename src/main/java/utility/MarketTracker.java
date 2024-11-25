package utility;

import data_access.StockDataAccessInterface;
import entity.Stock;
import utility.exceptions.RateLimitExceededException;
import view.ViewManager;
import view.view_events.UpdateStockEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MarketTracker {

    // market information update interval in milliseconds
    // initial interval in milliseconds
    private static final long INITIAL_UPDATE_MARKET_INTERVAL
            = Long.parseLong(MarketTrackerConfigLoader.getMarketTrackerProperty("INITIAL_UPDATE_MARKET_INTERVAL"));
    // interval adjustment rate in milliseconds
    private static final long UPDATE_INTERVAL_ADJUSTMENT_RATE
            = Long.parseLong(MarketTrackerConfigLoader.getMarketTrackerProperty("UPDATE_INTERVAL_ADJUSTMENT_RATE"));
    // number of rounds without rate limit
    private static final int ROUNDS_WITHOUT_RATE_LIMIT_TO_DECREASE
            = Integer.parseInt(MarketTrackerConfigLoader.getMarketTrackerProperty("ROUNDS_WITHOUT_RATE_LIMIT_TO_DECREASE"));

    // thread-safe Singleton instance
    private static volatile MarketTracker instance = null;

    // use read-write lock to ensure stock data is not read during update
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Map<String, Stock> stocks = new ConcurrentHashMap<>();
    private volatile long currentUpdateInterval = INITIAL_UPDATE_MARKET_INTERVAL;
    private int roundsWithoutRateLimit = 0;
    private StockDataAccessInterface dataAccess;
    private boolean initialized = false;
    private ScheduledExecutorService scheduler;

    private MarketTracker() {
        // Don't start updating prices until initialized
    }

    public static MarketTracker Instance() {
        if (instance == null) {
            synchronized (MarketTracker.class) {
                if (instance == null) {
                    instance = new MarketTracker();
                }
            }
        }
        return instance;
    }

    // initialize the stock market with data access object
    public synchronized void initialize(StockDataAccessInterface dataAccess) {
        if (this.initialized) {
            throw new IllegalStateException("MarketTracker is already initialized.");
        }
        this.dataAccess = dataAccess;
        this.initialized = true;
        updateStocks();  // First update
        startUpdatingStockPrices();  // Then start periodic updates
    }

    public Optional<Stock> getStock(String ticker) {
        lock.readLock().lock();
        try {
            return Optional.ofNullable(stocks.get(ticker));
        } finally {
            lock.readLock().unlock();
        }
    }

    public List<Stock> getStocks() {
        lock.readLock().lock();
        try {
            return new ArrayList<>(stocks.values());
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Update the stock information periodically based on API rate limit
     * <p>
     * The update rate initially INITIAL_UPDATE_MARKET_INTERVAL
     * When the API rate limit exceeds, increase update interval by UPDATE_INTERVAL_ADJUSTMENT_RATE
     * If API rate limit not exceeded in consecutive ROUNDS_WITHOUT_RATE_LIMIT_TO_DECREASE,
     * decrease update interval by UPDATE_INTERVAL_ADJUSTMENT_RATE until reaching INITIAL_UPDATE_MARKET_INTERVAL
     * </p>
     */
    public void updateStocks() {
        lock.writeLock().lock();
        try {
            if (dataAccess == null) {
                throw new IllegalStateException("MarketTracker has not been initialized with a data access object.");
            }

            // retrieve stock information from data access object
            Map<String, Stock> newStocks = dataAccess.getStocks();
            for (Map.Entry<String, Stock> entry : newStocks.entrySet()) {
                String ticker = entry.getKey();
                Stock newStock = entry.getValue();
                Stock existingStock = stocks.get(ticker);
                if (existingStock != null) {
                    existingStock.updatePrice(newStock.getMarketPrice());
                } else {
                    stocks.put(ticker, newStock);
                }
            }

            // if no exception, increment the rounds counter
            roundsWithoutRateLimit++;
            // check if it's time to reduce the interval
            if (roundsWithoutRateLimit >= ROUNDS_WITHOUT_RATE_LIMIT_TO_DECREASE &&
                    currentUpdateInterval > INITIAL_UPDATE_MARKET_INTERVAL) {
                currentUpdateInterval = Math.max(currentUpdateInterval - UPDATE_INTERVAL_ADJUSTMENT_RATE,
                        INITIAL_UPDATE_MARKET_INTERVAL);
                roundsWithoutRateLimit = 0; // reset counter after adjustment
                restartScheduler(); // restart the scheduler with new interval
            }

            // broadcast stock update to view
            System.out.println("Broadcasting stock update...");
            ViewManager.Instance().broadcastEvent(new UpdateStockEvent(getStocks()));

            // notify observer of price update
            System.out.println("Notifying observers...");
            MarketObserver.Instance().onMarketUpdate();
        } catch (RateLimitExceededException e) {
            // on rate limit, increase the update interval and reset the rounds counter
            currentUpdateInterval += UPDATE_INTERVAL_ADJUSTMENT_RATE;
            roundsWithoutRateLimit = 0;
            restartScheduler(); // restart scheduler with new interval
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Starts a background thread to update stock prices at fixed intervals.
     */
    public synchronized void startUpdatingStockPrices() {
        if (scheduler != null && !scheduler.isShutdown()) {
            throw new IllegalStateException("Stock price updating is already running.");
        }
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this::updateStocks, currentUpdateInterval, currentUpdateInterval, TimeUnit.MILLISECONDS);
    }

    /**
     * Stops the periodic stock price updates.
     */
    public synchronized void stopUpdatingStockPrices() {
        if (scheduler != null) {
            scheduler.shutdown();
            try {
                if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                    scheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                scheduler.shutdownNow();
            }
            scheduler = null;
        }
    }

    /**
     * Restarts the scheduler with the current update interval.
     */
    private synchronized void restartScheduler() {
        stopUpdatingStockPrices();
        startUpdatingStockPrices();
    }
}
