package utility;

import java.util.HashMap;
import java.util.Map;

// This class manages all the service instances for global access
// e.g. controllers, presenters, and interactors
// TODO: singleton pattern
public class ServiceManager {
    private static final Map<Class<?>, Object> services = new HashMap<>();

    public static <T> void registerService(Class<T> key, T service) {
        services.put(key, service);
    }

    public static <T> T getService(Class<T> key) {
        return key.cast(services.get(key));
    }
}