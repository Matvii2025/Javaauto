package com.pages.decorators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CoreActions implements UiActions {
    private final Duration timeout = Duration.ofSeconds(20);

    @Override public void click(WebDriver driver, WebElement e) {
        new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(e)).click();
    }
    @Override public void type(WebDriver driver, WebElement e, String text) {
        WebElement el = new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(e));
        el.clear(); el.sendKeys(text);
    }
    @Override public String textOf(WebDriver driver, WebElement e) {
        return new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(e)).getText();
    }
}