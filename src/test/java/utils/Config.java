package utils;

import java.io.InputStream;
import java.util.Properties;

/** Singleton конфігурації для тестів (env, headless, credentials). */
public class Config {
    public final String baseUrl;
    public final boolean headless;
    public final String username;
    public final String password;

    private static volatile Config INSTANCE;

    private Config(String baseUrl, boolean headless, String username, String password) {
        this.baseUrl = baseUrl;
        this.headless = headless;
        this.username = username;
        this.password = password;
    }

    public static Config get() {
        if (INSTANCE == null) {
            synchronized (Config.class) {
                if (INSTANCE == null) INSTANCE = loadInternal();
            }
        }
        return INSTANCE;
    }

    public static void reload() {
        synchronized (Config.class) { INSTANCE = loadInternal(); }
    }

    private static Config loadInternal() {
        String env = System.getProperty("env", "dev");
        String propsPath = "env/" + env + ".properties"; // src/test/resources/env/*.properties

        Properties p = new Properties();
        try (InputStream in = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(propsPath)) {
            if (in != null) p.load(in);
        } catch (Exception ignored) { }

        String baseUrl   = sysOrProp("base.url",          p.getProperty("base.url", "https://example.com"));
        boolean headless = Boolean.parseBoolean(sysOrProp("headless", p.getProperty("browser.headless","false")));

        // ⬇️ ДОДАНО: читаємо креденшали (можна перекрити -Dusername/-Dpassword)
        String username  = sysOrProp("username",          p.getProperty("username", "user"));
        String password  = sysOrProp("password",          p.getProperty("password", "pass"));

        return new Config(baseUrl, headless, username, password);
    }

    private static String sysOrProp(String key, String fallback) {
        String v = System.getProperty(key);
        return (v != null && !v.isBlank()) ? v : fallback;
    }

    // ⬇️ ДОДАНО: щоб не міняти твоє User.fromConfig()
    public static String username() { return get().username; }
    public static String password() { return get().password; }
}