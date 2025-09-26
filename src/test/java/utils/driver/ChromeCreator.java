package utils.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.Config;

public class ChromeCreator extends BrowserCreator {
    @Override public WebDriver create(Config cfg) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions co = new ChromeOptions();
        if (cfg.headless) co.addArguments("--headless=new","--disable-gpu");
        co.addArguments("--window-size=1920,1080");
        return new ChromeDriver(co);
    }
}