package pages.decorators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface UiActions {
    void click(WebDriver driver, WebElement e);
    void type(WebDriver driver, WebElement e, String text);
    String textOf(WebDriver driver, WebElement e);
}