package tests;

import base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutTest extends BaseTest {

    @Test
    @DisplayName("Успішний вихід після входу")
    public void testLogout() {
        driver.get("https://the-internet.herokuapp.com/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("tomsmith", "SuperSecretPassword!");

        // Натискаємо кнопку Logout
        driver.findElement(By.cssSelector("a.button.secondary.radius")).click();

        // Перевіряємо повідомлення після логауту
        String message = driver.findElement(By.id("flash")).getText().trim();
        assertTrue(message.contains("You logged out of the secure area!"),
                "Expected logout message to contain: 'You logged out of the secure area!'. Actual: " + message);
    }
}