package org.example.hardcore.config;

public class Config {
    public final String baseUrl;
    public final String tempMailUrl;
    public final boolean headless;

    private static volatile Config INSTANCE;

    private Config(String baseUrl, String tempMailUrl, boolean headless) {
        this.baseUrl = baseUrl;
        this.tempMailUrl = tempMailUrl;
        this.headless = headless;
    }

    public static Config get() {
        if (INSTANCE == null) {
            synchronized (Config.class) {
                if (INSTANCE == null) {
                    INSTANCE = loadInternal();
                }
            }
        }
        return INSTANCE;
    }

    private static Config loadInternal() {
        String envStr = System.getProperty("env", "dev");
        Environment env = Environment.from(envStr);

        PropertyLoader loader = new PropertyLoader(env);
        String baseUrl = sysOrProp("base.url", loader.get("base.url"));
        String tempMail = sysOrProp("tempmail.url", loader.get("tempmail.url"));
        boolean headless = boolSysOrProp("headless", loader.get("browser.headless"));

        return new Config(baseUrl, tempMail, headless);
    }

    private static String sysOrProp(String key, String fallback) {
        String v = System.getProperty(key);
        return (v != null && !v.isBlank()) ? v : fallback;
    }

    private static boolean boolSysOrProp(String key, String fallback) {
        String v = System.getProperty(key);
        return Boolean.parseBoolean(v != null ? v : fallback);
    }
}