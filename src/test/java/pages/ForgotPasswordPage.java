package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage {

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "form_submit")
    private WebElement retrieveButton;

    @FindBy(tagName = "h1")
    private WebElement resultHeading;

    public ForgotPasswordPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void submitForm() {
        retrieveButton.click();
    }

    public String getHeading() {
        return resultHeading.getText().trim();
    }
}