package base;

import listeners.ScreenshotOnFailureExtension;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;

import java.time.Duration;

@ExtendWith(ScreenshotOnFailureExtension.class)
public class BaseTest {
    public WebDriver driver;                 // важливо: доступно для extension
    protected WebDriverWait wait;
    protected final Logger log = LogManager.getLogger(getClass());

    @BeforeEach
    void setUp() {
        log.info("[RUN CONFIG] env={}, remote={}, browser={}, gridUrl={}, headless={}",
                System.getProperty("env","dev"),
                System.getProperty("remote","false"),
                System.getProperty("browser","chrome"),
                System.getProperty("gridUrl","http://localhost:4444/wd/hub"),
                System.getProperty("headless","false"));
        driver = DriverFactory.createDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }
}