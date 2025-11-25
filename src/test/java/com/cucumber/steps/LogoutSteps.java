package com.cucumber.steps;

import com.cucumber.hooks.Hooks;
import com.pages.LoginPage;
import com.pages.SecureAreaPage;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogoutSteps {

    LoginPage loginPage;
    SecureAreaPage secureAreaPage;

    @Given("User logs in with valid credentials")
    public void loginWithValidCredentials() {
        loginPage = new LoginPage(Hooks.driver, Hooks.wait);
        loginPage.open();
        secureAreaPage = loginPage.loginAs("tomsmith", "SuperSecretPassword!");
    }

    @When("User clicks Logout button")
    public void clickLogout() {
        secureAreaPage.logout();
    }

    @Then("User should be redirected to Login page")
    public void verifyRedirectToLogin() {
        WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/login"));
        Assert.assertTrue(Hooks.driver.getCurrentUrl().contains("/login"));
    }
}