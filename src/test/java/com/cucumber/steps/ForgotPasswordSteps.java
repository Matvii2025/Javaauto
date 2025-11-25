package com.cucumber.steps;

import com.cucumber.hooks.Hooks;
import com.pages.ForgotPasswordPage;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class ForgotPasswordSteps {

    ForgotPasswordPage page;

    @Given("User opens the Forgot Password page")
    public void openForgotPasswordPage() {
        Hooks.driver.get("https://the-internet.herokuapp.com/forgot_password");
        page = new ForgotPasswordPage(Hooks.driver, Hooks.wait);
    }

    @When("User enters email {string}")
    public void enterEmail(String email) {
        page.enterEmail(email);
    }

    @When("User submits the forgot password form")
    public void submitForm() {
        page.submitForm();
    }

    @Then("Heading should be {string}")
    public void verifyHeading(String expected) {
        String heading = page.getHeading();
        Assert.assertEquals(expected, heading);
    }
}