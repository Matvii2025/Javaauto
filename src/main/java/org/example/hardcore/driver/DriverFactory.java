package org.example.hardcore.driver;

import org.example.hardcore.config.Config;
import org.example.hardcore.driver.creator.BrowserCreator;
import org.openqa.selenium.WebDriver;

/**
 * Factory Method: delegates driver creation to concrete BrowserCreator.
 */
public class DriverFactory {
    public static WebDriver create(Config cfg) {
        String browser = System.getProperty("browser", "chrome");
        BrowserCreator creator = BrowserCreator.of(browser);
        return creator.create(cfg);
    }
}