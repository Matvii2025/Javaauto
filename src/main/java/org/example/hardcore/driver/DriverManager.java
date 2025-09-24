package org.example.hardcore.driver;
import org.openqa.selenium.WebDriver;

/**
 * SRP: only manages thread-local driver lifecycle.
 */
public class DriverManager {
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static WebDriver getDriver() { return tlDriver.get(); }
    public static void setDriver(WebDriver driver) { tlDriver.set(driver); }

    public static void quit() {
        WebDriver d = tlDriver.get();
        if (d != null) {
            d.quit();
            tlDriver.remove();
        }
    }
}