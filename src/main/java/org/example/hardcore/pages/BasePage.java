package org.example.hardcore.pages;

import org.example.hardcore.logging.Log;
import org.example.hardcore.util.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * BasePage follows SRP (only UI interactions helpers) and DIP (depends on WebDriver abstraction).
 */
public abstract class BasePage {
    protected final WebDriver driver;
    protected final Log log = Log.get(getClass());

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void click(WebElement e) { Waits.waitToBeClickable(driver, e).click(); log.action("Click: %s", e); }
    protected void type(WebElement e, String text) { WebElement el = Waits.waitVisible(driver, e); el.clear(); el.sendKeys(text); log.action("Type '%s' into %s", text, e); }
    protected String textOf(WebElement e) { return Waits.waitVisible(driver, e).getText(); }
}