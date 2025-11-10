package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SecureAreaPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By flashSuccess = By.cssSelector("div.flash.success");
    private final By logoutBtn    = By.cssSelector("a.button.secondary.radius[href='/logout']");

    public SecureAreaPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public SecureAreaPage waitLoaded() {
        wait.until(ExpectedConditions.urlContains("/secure"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(flashSuccess));
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutBtn));
        return this;
    }

    public void logout() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(logoutBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        btn.click();
    }
}