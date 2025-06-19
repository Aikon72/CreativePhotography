import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class Config {
    private static final Properties properties = new Properties();
    private static final File CONFIG_FILE;
    private static final Map<String, String> configCache = new ConcurrentHashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(Config.class);

    static {
        CONFIG_FILE = new File("config.properties");
        loadProperties();
    }

    private static void loadProperties() {
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
        } catch (FileNotFoundException e) {
            saveProperties();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file", e);
        }
    }

    private static void saveProperties() {
        try (OutputStream output = new FileOutputStream(CONFIG_FILE)) {
            properties.store(output, "Updated configuration");
        } catch (IOException e) {
            throw new RuntimeException("Failed to save configuration", e);
        }
    }
    public static String getProperty(String key) {
        return configCache.computeIfAbsent(key, k -> {
            String value = properties.getProperty(key);
            if (value == null || value.trim().isEmpty()) {
                throw new RuntimeException("Required property '" + key + "' not found or empty");
            }
            return value;
        });
    }
    public static void setProperty(String key, String value) {
        properties.setProperty(key, value);
        logger.info("Updated {} = {}", key, value);
        saveProperties();
    }
}