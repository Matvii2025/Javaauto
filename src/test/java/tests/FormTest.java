package tests;

import base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ForgotPasswordPage;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormTest extends BaseTest {

    @Test
    @DisplayName("Відправлення форми 'Забув пароль'")
    public void testFormSubmission() {
        driver.get("https://the-internet.herokuapp.com/forgot_password");

        ForgotPasswordPage page = new ForgotPasswordPage(driver);
        page.enterEmail("user@example.com");
        page.submitForm();

        String heading = page.getHeading();
        assertEquals("Internal Server Error", heading); // демо-сайт завжди повертає це
    }
}