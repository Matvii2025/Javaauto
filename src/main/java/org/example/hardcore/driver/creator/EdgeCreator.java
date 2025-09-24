package org.example.hardcore.driver.creator;

import org.example.hardcore.config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeCreator extends BrowserCreator {
    @Override public WebDriver create(Config cfg) {
        WebDriverManager.edgedriver().setup();
        EdgeOptions eo = new EdgeOptions();
        if (cfg.headless) eo.addArguments("--headless=new", "--disable-gpu");
        return new EdgeDriver(eo);
    }
}