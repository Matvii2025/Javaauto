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
import utils.Config;

import java.time.Duration;

@ExtendWith(ScreenshotOnFailureExtension.class)
public abstract class BaseTest {
    public WebDriver driver;
    protected WebDriverWait wait;
    protected final Logger log = LogManager.getLogger(getClass());
    protected Config cfg;

    @BeforeEach
    void setUp() {
        cfg = Config.get(); // Singleton
        log.info("[RUN CONFIG] env={}, browser={}, headless={}",
                System.getProperty("env","dev"),
                System.getProperty("browser","chrome"),
                cfg.headless);

        driver = DriverFactory.createDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
        log.info("Driver quit");
    }
}