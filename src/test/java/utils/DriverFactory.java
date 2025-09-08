package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;

public class DriverFactory {

    public static WebDriver createDriver() {
        String browser  = System.getProperty("browser",  Config.browser()  != null ? Config.browser()  : "chrome").toLowerCase();
        boolean remote  = Boolean.parseBoolean(System.getProperty("remote",  String.valueOf(Config.remote())));
        String gridUrl  = System.getProperty("gridUrl",  Config.gridUrl()  != null ? Config.gridUrl()  : "http://localhost:4444/wd/hub");
        boolean headless= Boolean.parseBoolean(System.getProperty("headless",String.valueOf(Config.headless())));

        WebDriver driver;

        switch (browser) {
        case "firefox": {
            FirefoxOptions o = new FirefoxOptions();
            o.setAcceptInsecureCerts(true);
            if (headless) o.addArguments("-headless");

            driver = remote ? new RemoteWebDriver(toUrl(gridUrl), o) : localFirefox(o);
            break;
        }
        default: { // chrome
            ChromeOptions o = new ChromeOptions();
            o.setAcceptInsecureCerts(true);
            if (headless) o.addArguments("--headless=new");
            o.addArguments("--window-size=1920,1080");

            driver = remote ? new RemoteWebDriver(toUrl(gridUrl), o) : localChrome(o);
        }
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        return driver;
    }

    private static URL toUrl(String s) {
        try { return new URL(s); }
        catch (Exception e) { throw new RuntimeException("Bad gridUrl: " + s, e); }
    }

    private static WebDriver localChrome(ChromeOptions o) {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(o);
    }

    private static WebDriver localFirefox(FirefoxOptions o) {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(o);
    }
}