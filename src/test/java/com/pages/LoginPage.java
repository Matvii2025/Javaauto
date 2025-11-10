package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final String url = "https://the-internet.herokuapp.com/login";

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    @FindBy(id = "flash")
    private WebElement messageElement;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public LoginPage open() {
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        return this;
    }

    public void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOf(usernameField)).clear();
        usernameField.sendKeys(username);

        wait.until(ExpectedConditions.visibilityOf(passwordField)).clear();
        passwordField.sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public String getMessage() {
        wait.until(ExpectedConditions.visibilityOf(messageElement));
        return messageElement.getText().trim();
    }

    public SecureAreaPage loginAs(String user, String pass) {
        login(user, pass);
        return new SecureAreaPage(driver, wait).waitLoaded();
    }
}
