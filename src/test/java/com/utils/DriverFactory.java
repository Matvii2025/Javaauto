package com.utils;

import org.openqa.selenium.WebDriver;
import com.utils.driver.BrowserCreator;

public class DriverFactory {
    public static WebDriver createDriver() {
        Config cfg = Config.get();
        String browser = System.getProperty("browser", "chrome");
        BrowserCreator creator = BrowserCreator.of(browser);
        return creator.create(cfg);
    }
}