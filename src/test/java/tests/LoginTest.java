package tests;

import base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Успішний вхід із валідними даними")
    public void testSuccessfulLogin() {
        driver.get("https://the-internet.herokuapp.com/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("tomsmith", "SuperSecretPassword!");

        String message = loginPage.getMessage();
        assertTrue(message.contains("You logged into a secure area!"),
                "Expected success message to contain: 'You logged into a secure area!'. Actual: " + message);
    }
}