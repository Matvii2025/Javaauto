package org.example.hardcore.driver.creator;

import org.example.hardcore.config.Config;
import org.openqa.selenium.WebDriver;

public abstract class BrowserCreator {
    public abstract WebDriver create(Config cfg);

    public static BrowserCreator of(String browser) {
        if (browser == null) return new ChromeCreator();
        switch (browser.trim().toLowerCase()) {
        case "firefox": return new FirefoxCreator();
        case "edge":    return new EdgeCreator();
        case "chrome":
        default:        return new ChromeCreator();
        }
    }
}
