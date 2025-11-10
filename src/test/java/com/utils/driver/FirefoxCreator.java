package com.utils.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import com.utils.Config;

public class FirefoxCreator extends BrowserCreator {
    @Override public WebDriver create(Config cfg) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions ffo = new FirefoxOptions();
        if (cfg.headless) ffo.addArguments("-headless");
        return new FirefoxDriver(ffo);
    }
}