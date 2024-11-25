package utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class for loading and accessing market tracker configuration properties.
 * The configuration is loaded from a properties file at "config/market-tracker-config.txt".
 */
public class MarketTrackerConfigLoader {

    private static final String MARKET_TRACKER_CONFIG = "config/market-tracker-config.txt";

    private static final Properties marketTrackerProperties = new Properties();

    static {
        loadMarketTrackerConfig();
    }

    /**
     * Loads the market tracker configuration from the specified file.
     * This method is called once when the class is initialized.
     *
     * @throws RuntimeException if the configuration file is not found or cannot be loaded.
     */
    private static void loadMarketTrackerConfig() {
        try (InputStream input = MarketTrackerConfigLoader.class
                .getClassLoader()
                .getResourceAsStream(MarketTrackerConfigLoader.MARKET_TRACKER_CONFIG)
        ) {
            if (input == null) {
                throw new RuntimeException("Unable to find configuration file: " + MarketTrackerConfigLoader.MARKET_TRACKER_CONFIG);
            }
            marketTrackerProperties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading configuration file", e);
        }
    }

    /**
     * Retrieves the value of a property from the loaded configuration.
     *
     * @param key The property key to retrieve.
     * @return The value of the property, or {@code null} if the key does not exist.
     */
    public static String getMarketTrackerProperty(String key) {
        return marketTrackerProperties.getProperty(key);
    }
}