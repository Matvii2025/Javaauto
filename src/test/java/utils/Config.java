package utils;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties props = new Properties();

    static {
        String env = System.getProperty("env");
        if (env == null || env.trim().isEmpty()) env = "dev";   // <-- без isBlank()
        String resource = "env/" + env.toLowerCase() + ".properties";

        try (InputStream is = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(resource)) {

            if (is == null) {
                throw new IllegalStateException("Resource not found on classpath: " + resource);
            }
            props.load(is);
            System.out.println("[CONFIG] Loaded: " + resource);
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Failed to load " + resource + ": " + e);
        }
    }

    private static String get(String key) {
        String sys = System.getProperty(key);
        if (sys != null && !sys.trim().isEmpty()) return sys;   // <-- без isBlank()
        return props.getProperty(key);
    }

    public static String baseUrl()   { return get("baseUrl"); }
    public static String username()  { return get("username"); }
    public static String password()  { return get("password"); }
    public static String browser()   { return get("browser"); }
    public static String gridUrl()   { return get("gridUrl"); }
    public static boolean remote()   { return Boolean.parseBoolean(get("remote")); }
    public static boolean headless() { return Boolean.parseBoolean(get("headless")); }
}