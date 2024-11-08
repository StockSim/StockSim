package data_access;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.timeseries.response.QuoteResponse;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;

import java.util.Map;

public class StockDataAccess implements IStockDataAccess {

    /**
     * @param ticker : the stock ticker of the stock
     * @return the price of the stock with the given ticker
     */
    @Override
    public double getStockPrice(String ticker) {
        return 0;
    }

    public void handleSuccess(TimeSeriesResponse response) {
        System.out.println("Successfully retrived data");
    }

    public void handleFailure(AlphaVantageException error) {
        System.out.println("Error retrived data");
    }

    /**
     * Get the prices of all stocks
     *
     * @return a hashmap with the stock ticker as the key and the stock price as the value.
     * It should contain all stocks in the database.
     */
    @Override
    public Map<String, Double> getStocks() {
        // API Key V70UK9YUS2MBA6YH

        String apiKey = "V70UK9YUS2MBA6YH";
        String symbol = "NVDA";

        final Config cfg = Config.builder()
                .key(apiKey)
                .timeOut(10)
                .build();

        AlphaVantage.api().init(cfg);

        QuoteResponse data =
                AlphaVantage.api()
                .timeSeries()
                .quote()
                .forSymbol(symbol)
                .fetchSync();
//                .onSuccess(e->handleSuccess((TimeSeriesResponse) e))
//                .onFailure(e->handleFailure(e))

        return Map.of(symbol, data.getPrice());
    }
}
