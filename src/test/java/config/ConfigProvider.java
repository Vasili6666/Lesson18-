package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProvider {
    private static final Properties props = new Properties();

    static {
        try (InputStream is = ConfigProvider.class.getClassLoader().getResourceAsStream("browserstack.properties")) {
            if (is != null) {
                props.load(is);
            } else {
                throw new RuntimeException("browserstack.properties not found in resources");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load browserstack.properties", e);
        }
    }

    public static String get(String key) {
        // Попробуем сначала environment variable с префиксом BS_
        String envValue = System.getenv("BS_" + key.toUpperCase());
        if (envValue != null && !envValue.isEmpty()) {
            return envValue;
        }

        // Потом берём из properties
        String propValue = props.getProperty(key);
        if (propValue != null && !propValue.isEmpty()) {
            return propValue;
        }

        throw new RuntimeException("Property or environment variable '" + key + "' not found");
    }
}
