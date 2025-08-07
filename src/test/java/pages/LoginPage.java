package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@id='flash']")
    private WebElement messageElement;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOf(usernameField)).clear();
        usernameField.sendKeys(username);

        wait.until(ExpectedConditions.visibilityOf(passwordField)).clear();
        passwordField.sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }
    public String getMessage() {
        wait.until(ExpectedConditions.visibilityOf(messageElement));
        return messageElement.getText().trim();
    }
}
