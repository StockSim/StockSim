package api;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class httpDataAccessTest {
    public static void main(String[] args) {
        String API_URL = "https://www.alphavantage.co/query";
        List<String> symbols = Arrays.asList("IBM", "MSFT", "LVLT");  // List of symbols
        String apiKey = "YOUR_API_KEY"; // Replace with your actual API key

        // Create HttpClient instance
        HttpClient client = HttpClient.newHttpClient();

        // Map to store each symbol's response
        Map<String, JSONObject> stockDataMap = new HashMap<>();

        // Loop through each symbol and make an individual API call
        for (String symbol : symbols) {
            // Build the full URL with query parameters
            String url = String.format("%s?function=GLOBAL_QUOTE&symbol=%s&apikey=%s", API_URL, symbol, apiKey);

            // Create an HttpRequest for a GET request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            try {
                // Send the request and get the response
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                // Parse JSON response and store it in the map with the symbol as the key
                JSONObject jsonResponse = new JSONObject(response.body());
                stockDataMap.put(symbol, jsonResponse);

                // Print each individual response for verification
                System.out.println("Response for " + symbol + ":");
                System.out.println(jsonResponse.toString(2)); // Pretty print JSON with 2 spaces indentation

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        // You now have all the responses stored in the stockDataMap
        // Access a particular symbol's data as follows:
        System.out.println("All collected data:");
        for (Map.Entry<String, JSONObject> entry : stockDataMap.entrySet()) {
            String symbol = entry.getKey();
            JSONObject data = entry.getValue();
            System.out.println("Data for " + symbol + ": " + data.toString(2));
        }


        // "08. previous close" - the price the stock closed at for the previous day; can use to determine recommended market overview stocks
    }
}
