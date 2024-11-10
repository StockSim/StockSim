package api;
import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.timeseries.response.QuoteResponse;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files


public class StockDataAccess implements IStockDataAccess {

    public void handleSuccess(TimeSeriesResponse response) {
        System.out.println("Successfully retrived data");
    }

    public void handleFailure(AlphaVantageException error) {
        System.out.println("Error retrived data");
    }

    private List<String> readDataFile(String fileName) {
        // TODO: return String of data seperated by commas to fit API required format
        List<String> data = new ArrayList<>();
        try {
            File myFile = new File(fileName);
            Scanner scanner = new Scanner(myFile);
            while (scanner.hasNextLine()) {
                String l = scanner.nextLine();
                data.add(l);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }

    /**
     * Get the prices of all stocks
     *
     * @return a hashmap with the stock ticker as the key and the stock price as the value.
     * It should contain all stocks in the database.
     */
    @Override
    public Map<String, Double> getStockData() {
        String apiKey = "V70UK9YUS2MBA6YH";
        String tickerDataFile = "src/java/api/tickerDataDemo.txt";

        // TODO: Generate tickers list based on tickerData file or
        //  "http://nasdaqtrader.com/dynamic/SymDir/nasdaqlisted.txt" for only US market
        List<String> tickers = readDataFile(tickerDataFile);

        // Init API
        final Config cfg = Config.builder()
                .key(apiKey)
                .timeOut(10)
                .build();
        AlphaVantage.api().init(cfg);

        // API calls
        for (String ticker : tickers) {
            QuoteResponse data =
                    AlphaVantage.api()
                            .timeSeries()
                            .quote()
                            .forSymbol(ticker)
                            .fetchSync();
//                .onSuccess(e->handleSuccess((TimeSeriesResponse) e))
//                .onFailure(e->handleFailure(e))
            System.out.println("Ticker: " + ticker + ", Market Price: " + data.getPrice() + ", Floating Stock: " + data.getVolume());
        }



//        String link = "http://nasdaqtrader.com/dynamic/SymDir/nasdaqlisted.txt";
//        try{
//            String text: String = URL(link).readText();
//        }
//        catch (IOException e){
//            System.out.println("Cannot retrieve data");
//        }
        return Map.of("ticker", 11.11);
    }

    public static void main(String[] args) {
        StockDataAccess stockDataAccess = new StockDataAccess();
        stockDataAccess.getStockData();
    }
}
