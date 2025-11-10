package com.tests;

import com.base.BaseTest;
import com.pages.LoginPage;
import com.pages.SecureAreaPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutTest extends BaseTest {

    @Test
    @DisplayName("Успішний вихід після входу")
    void testLogout() {
        SecureAreaPage secure = new LoginPage(driver, wait)
                .open()
                .loginAs("tomsmith", "SuperSecretPassword!");

        secure.logout();

        // перевірка: знову на сторінці логіну
        wait.until(ExpectedConditions.urlContains("/login"));
        assertTrue(driver.getCurrentUrl().contains("/login"));
    }
}