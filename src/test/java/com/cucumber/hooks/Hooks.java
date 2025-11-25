package com.cucumber.hooks;

import com.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @Before
    public void setUp() {
        driver = DriverFactory.createDriver();   // <-- все правильно
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}