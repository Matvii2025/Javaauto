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
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        boolean remote = Boolean.parseBoolean(System.getProperty("remote", "false"));
        String gridUrl = System.getProperty("gridUrl", "http://localhost:4444/wd/hub");
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        WebDriver driver;

        switch (browser) {
        case "firefox": {
            FirefoxOptions opts = new FirefoxOptions();
            opts.setAcceptInsecureCerts(true);
            if (headless) opts.addArguments("-headless");

            if (remote) {
                driver = new RemoteWebDriver(toUrl(gridUrl), opts);
            } else {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(opts);
            }
            break;
        }
        case "chrome":
        default: {
            ChromeOptions opts = new ChromeOptions();
            opts.setAcceptInsecureCerts(true);
            if (headless) opts.addArguments("--headless=new");

            if (remote) {
                driver = new RemoteWebDriver(toUrl(gridUrl), opts);
            } else {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(opts);
            }
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
}
