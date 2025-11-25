package com.cucumber.steps;

import com.cucumber.hooks.Hooks;
import com.pages.LoginPage;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class LoginSteps {

    LoginPage loginPage;

    @Given("User is on the Login page")
    public void openLoginPage() {
        Hooks.driver.get("https://the-internet.herokuapp.com/login");
        loginPage = new LoginPage(Hooks.driver, Hooks.wait);
    }

    @When("User enters username {string}")
    public void enterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @When("User enters password {string}")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("User clicks Login button")
    public void clickLogin() {
        loginPage.clickLogin();
    }

    @Then("Login message should contain {string}")
    public void verifyMessage(String expected) {
        String msg = loginPage.getMessage();
        Assert.assertTrue(msg.contains(expected));
    }
}