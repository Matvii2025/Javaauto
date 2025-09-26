package utils.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import utils.Config;

public class EdgeCreator extends BrowserCreator {
    @Override public WebDriver create(Config cfg) {
        WebDriverManager.edgedriver().setup();
        EdgeOptions eo = new EdgeOptions();
        if (cfg.headless) eo.addArguments("--headless=new","--disable-gpu");
        return new EdgeDriver(eo);
    }
}