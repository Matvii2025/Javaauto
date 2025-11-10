package com.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import java.util.List;

public class ActionsUtil {
    private final WebDriver driver;
    public ActionsUtil(WebDriver driver) { this.driver = driver; }

    public void dragAndDrop(WebElement source, WebElement target) {
        new Actions(driver)
                .moveToElement(source)
                .clickAndHold(source)
                .pause(200)
                .moveToElement(target)
                .pause(200)
                .release(target)
                .build().perform();
    }

    public String contextClickAndGetAlertText(WebElement element) {
        new Actions(driver).moveToElement(element).contextClick(element).perform();
        try {
            Alert alert = driver.switchTo().alert();
            String txt = alert.getText();
            alert.accept();
            return txt;
        } catch (NoAlertPresentException e) {
            return null;
        }
    }

    public void multiSelectWithCtrl(List<WebElement> items) {
        Actions a = new Actions(driver).keyDown(Keys.CONTROL);
        for (WebElement it : items) a.click(it);
        a.keyUp(Keys.CONTROL).build().perform();
    }

    public void typeWithKeyboard(WebElement target, CharSequence text) {
        new Actions(driver).click(target).sendKeys(text).perform();
    }
}