package org.example.hardcore.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/** Спільні очікування для PageObject-ів. */
public class Waits {
    private static final Duration TIMEOUT = Duration.ofSeconds(20);

    public static WebElement waitVisible(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitToBeClickable(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(element));
    }
}