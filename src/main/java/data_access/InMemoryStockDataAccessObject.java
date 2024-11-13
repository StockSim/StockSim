package data_access;

import entity.Stock;

import java.util.Map;

public class InMemoryStockDataAccessObject implements IStockDataAccess {

    @Override
    public Map<String, Stock> getStocks() {
        Map<String, Stock> stocks = new java.util.HashMap<>();
        stocks.put("XXXX", new Stock("XXXX", 100.0));
        stocks.put("YYYY", new Stock("YYYY", 200.0));
        stocks.put("ZZZZ", new Stock("ZZZZ", 300.0));
        return stocks;
    }
}


