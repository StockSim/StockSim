package data_access;

import entity.Stock;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StockDataAccessObject implements IStockDataAccess {
    private static final String BASE_URL = "https://finnhub.io/api/v1";
    private final OkHttpClient client;
    private final String apiKey;

    public StockDataAccessObject(){
        this.client = new OkHttpClient();

        // Load .env.local file and get the API token
        Dotenv dotenv = Dotenv.configure().filename(".env.local").load();
        this.apiKey = dotenv.get("STOCK_API_KEY");
    }

    /**
     * Get the prices of all stocks
     *
     * @return a hashmap with the stock ticker as the key and the Stock entity as the value.
     * It should contain all stocks in the database.
     */
    @Override
    public Map<String, Stock> getStocks() {
        // TODO: implement api call limit exceeded exception/notification (Error 429)
        Map<String, Stock> stocks = new HashMap<>();

        try {
            // Reads content in config/tickers text file
            InputStream inputStream = getClass().getResourceAsStream("/tickers.txt");

            if (inputStream == null) {
                throw new FileNotFoundException("Ticker resource file not found.");
            }
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String url = String.format("%s/quote?symbol=%s&token=%s", BASE_URL, line, apiKey);
                Request request = new Request.Builder().url(url).build();
                try (Response response = client.newCall(request).execute()) {
                    if (response.isSuccessful()) {
                        String responseBody = response.body().string();
                        JSONObject jsonObject = new JSONObject(responseBody);
                        Stock stock = new Stock(line, jsonObject.getDouble("c"));
                        stocks.put(line, stock);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return stocks;
    }
}