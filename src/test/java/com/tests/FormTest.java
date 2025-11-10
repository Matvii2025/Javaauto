package com.tests;

import com.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.pages.ForgotPasswordPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("regression")
public class FormTest extends BaseTest {

    @Test
    @DisplayName("Відправлення форми 'Забув пароль'")
    public void testFormSubmission() {
        driver.get("https://the-internet.herokuapp.com/forgot_password");

        ForgotPasswordPage page = new ForgotPasswordPage(driver, wait);
        page.enterEmail("user@example.com");
        page.submitForm();

        String heading = page.getHeading();
        assertEquals("Internal Server Error", heading); // демо-сайт завжди повертає це
    }
}