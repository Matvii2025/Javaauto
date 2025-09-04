package base;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeAll
    void printRunConfig() {
        System.out.printf("[RUN CONFIG] remote=%s, browser=%s, gridUrl=%s, headless=%s%n",
                System.getProperty("remote", "false"),
                System.getProperty("browser", "chrome"),
                System.getProperty("gridUrl", "http://localhost:4444/wd/hub"),
                System.getProperty("headless", "false"));
    }

    @BeforeEach
    public void setUp() {
        driver = DriverFactory.createDriver();                 // локально або GRID — залежно від -Dremote
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}