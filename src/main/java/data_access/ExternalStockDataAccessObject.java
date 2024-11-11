//package data_access;
//
//import io.github.cdimascio.dotenv.Dotenv;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import org.json.JSONObject;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//public class ExternalStockDataAccessObject implements IStockDataAccess {
//    private static final String BASE_URL = "https://www.alphavantage.co/query?function=REALTIME_BULK_QUOTES";
//    private final OkHttpClient client;
//    private final String apiKey;
//
//    public ExternalStockDataAccessObject() {
//        this.client = new OkHttpClient();
//
//        // Load .env.local file and get the API token
//        Dotenv dotenv = Dotenv.configure().filename(".env.slocal").load();
//        this.apiKey = dotenv.get("STOCK_API_TOKEN");
//    }
//
//    @Override
//    public double getStockPrice(String ticker) {
//        Map<String, Double> stocks = getStocks(List.of(ticker));
//        return stocks.getOrDefault(ticker, 0.0);
//    }
//
//    @Override
//    public Map<String, Double> getStocks(List<String> symbols) {
//        Map<String, Double> stockPrices = new HashMap<>();
//        if (symbols == null || symbols.isEmpty()) {
//            return stockPrices;
//        }
//
//        try {
//            String symbolList = symbols.stream().collect(Collectors.joining(","));
//            String urlStr = BASE_URL + "&symbol=" + symbolList + "&apikey=" + this.apiKey;
//
//            Request request = new Request.Builder()
//                    .url(urlStr)
//                    .build();
//
//            try (Response response = client.newCall(request).execute()) {
//                if (response.isSuccessful() && response.body() != null) {
//                    String responseBody = response.body().string();
//
//                    JSONObject jsonResponse = new JSONObject(responseBody);
//                    for (Object quote : jsonResponse.getJSONArray("Realtime Bulk Quotes")) {
//                        JSONObject stock = (JSONObject) quote;
//                        String symbol = stock.getString("symbol");
//                        double price = stock.getDouble("price");
//                        stockPrices.put(symbol, price);
//                    }
//                } else {
//                    System.out.println("GET failed: " + response.code());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return stockPrices;
//    }
//}