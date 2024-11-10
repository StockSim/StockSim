package api;
import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.timeseries.response.QuoteResponse;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class StockDataAccess implements IStockDataAccess {
    private List<String> fileData = new ArrayList<>();

    private StockDataAccess() {
        this("tickerDataDemo.txt");
    }

    public StockDataAccess(String fileName) {

        // TODO: return String of data seperated by commas to fit API required format

        try {
            InputStream inputStream = getClass().getResourceAsStream("/tickerDataDemo.txt");
            if (inputStream == null) {
                throw new FileNotFoundException("Resource not found.");
            }
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                String l = scanner.nextLine();
                fileData.add(l);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
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
    public Map<String, Double> getStockData() {
        String apiKey = "V70UK9YUS2MBA6YH";
        String tickerDataFile = "src/java/api/tickerDataDemo.txt";

        // TODO: Generate tickers list based on tickerData file or
        //  "http://nasdaqtrader.com/dynamic/SymDir/nasdaqlisted.txt" for only US market

        // Init API
        final Config cfg = Config.builder()
                .key(apiKey)
                .timeOut(10)
                .build();
        AlphaVantage.api().init(cfg);
        System.out.println(fileData);
        // API calls
//        for (String ticker : fileData) {
//            QuoteResponse data =
//                    AlphaVantage.api()
//                            .timeSeries()
//                            .quote()
//                            .forSymbol(ticker)
//                            .fetchSync();
////                            .onSuccess(e->handleSuccess((TimeSeriesResponse) e))
////                            .onFailure(e->handleFailure(e))
//            System.out.println("Ticker: " + ticker + ", Market Price: " + data.getPrice() + ", Floating Stock: " + data.getVolume());
//        }
        QuoteResponse data =
                AlphaVantage.api()
                        .timeSeries()
                        .quote()
                        .forSymbol(fileData.get(0))
                        .fetchSync();
        System.out.println(data.getPrice());

        final Config cfg1 = Config.builder()
                .key(apiKey)
                .timeOut(10)
                .build();
        AlphaVantage.api().init(cfg1);

        QuoteResponse data1 =
                AlphaVantage.api()
                        .timeSeries()
                        .quote()
                        .forSymbol(fileData.get(1))
                        .fetchSync();
        System.out.println(data1.getPrice());




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
