package org.example.hardcore.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
    private final Properties props = new Properties();

    public PropertyLoader(Environment env) {
        try (InputStream in = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(env.path())) {
            if (in == null) {
                throw new RuntimeException("Properties not found on classpath: " + env.path());
            }
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties for " + env, e);
        }
    }
    public String get(String key) {
        return props.getProperty(key);
    }
    public int getInt(String key, int def) {
        String v = props.getProperty(key);
        return v != null ? Integer.parseInt(v) : def;
    }

    public boolean getBool(String key, boolean def) {
        String v = props.getProperty(key);
        return v != null ? Boolean.parseBoolean(v) : def;
    }
}