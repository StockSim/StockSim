package data_access;

import java.util.Map;

public class InMemoryStockDataAccessObject implements IStockDataAccess {
    private Map<String, Double> stocks;

    @Override
    public double getStockPrice(String ticker) {
        return stocks.get(ticker);
    }

    @Override
    public Map<String, Double> getStocks() {
        Map<String, Double> stocks = new java.util.HashMap<>();
        stocks.put("XXXX", 100.0);
        stocks.put("YYYY", 200.0);
        stocks.put("ZZZZ", 300.0);
        this.stocks = stocks;
        return stocks;
    }
}


