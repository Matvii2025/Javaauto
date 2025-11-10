package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ForgotPasswordPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By emailInput = By.id("email");
    private final By submitBtn  = By.id("form_submit");

    private final By resultHeader = By.tagName("h1");

    public ForgotPasswordPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public ForgotPasswordPage enterEmail(String email) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        input.clear();
        input.sendKeys(email);
        return this;
    }

    public ForgotPasswordPage submitForm() {
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
        return this;
    }

    /** Чекаємо появу заголовка на результат-сторінці і повертаємо текст */
    public String getHeading() {
        WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement h = localWait.until(
                ExpectedConditions.visibilityOfElementLocated(resultHeader)
        );
        return h.getText().trim();
    }
}