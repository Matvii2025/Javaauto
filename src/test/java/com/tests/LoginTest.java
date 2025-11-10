package com.tests;

import com.base.BaseTest;
import com.utils.ScreenshotUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("smoke")
public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Успішний вхід із валідними даними")
    public void testSuccessfulLogin() {
        driver.get("https://the-internet.herokuapp.com/login");

        LoginPage loginPage = new LoginPage(driver, wait).open();
        loginPage.login("tomsmith", "SuperSecretPassword!");

        String message = loginPage.getMessage();
        assertTrue(message.contains("You logged into a secure area!"),
                "Expected success message to contain: 'You logged into a secure area!'. Actual: " + message);
        ScreenshotUtil.take(driver, "contextMenu_showsAlert");
    }
}