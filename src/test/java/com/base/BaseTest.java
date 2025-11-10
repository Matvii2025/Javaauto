package com.base;

import com.listeners.ScreenshotOnFailureExtension;
import com.utils.Config;
import com.utils.DriverFactory;
import io.qameta.allure.junit5.AllureJunit5;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.junit.jupiter.api.TestInfo;
import com.utils.ScreenshotUtil;

@ExtendWith(AllureJunit5.class)
public abstract class BaseTest {

    public WebDriver driver;
    protected WebDriverWait wait;
    protected final Logger log = LogManager.getLogger(getClass());
    protected Config cfg;

    @BeforeEach
    void setUp() {
        cfg = Config.get();
        log.info("[RUN CONFIG] env={}, browser={}, headless={}",
                System.getProperty("env", "dev"),
                System.getProperty("browser", "chrome"),
                cfg.headless);

        driver = DriverFactory.createDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown(TestInfo info) {
        try {
            if (driver != null) {
                ScreenshotUtil.take(driver, info.getDisplayName());
                driver.quit();
                log.info("Driver quit");
            }
        } catch (Exception e) {
            log.error("Error in tearDown", e);
            if (driver != null) {
                driver.quit();
            }
        }
    }
}