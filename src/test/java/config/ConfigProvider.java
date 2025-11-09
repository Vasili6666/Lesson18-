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
            throw new RuntimeException(e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
