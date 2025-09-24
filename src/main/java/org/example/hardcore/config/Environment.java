package org.example.hardcore.config;

public enum Environment {
    DEV("env/dev.properties"),
    QA("env/qa.properties");

    private final String path;
    Environment(String path) { this.path = path; }
    public String path() { return path; }

    public static Environment from(String value) {
        if (value == null) return DEV;
        try { return Environment.valueOf(value.trim().toUpperCase()); }
        catch (IllegalArgumentException e) { return DEV; }
    }
}
