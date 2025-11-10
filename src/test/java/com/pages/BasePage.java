package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.pages.decorators.CoreActions;
import com.pages.decorators.LoggingActions;
import com.pages.decorators.UiActions;

public abstract class BasePage {
    protected final WebDriver driver;
    private final UiActions actions = new LoggingActions(new CoreActions());

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    protected void click(WebElement e) { actions.click(driver, e); }
    protected void type(WebElement e, String text) { actions.type(driver, e, text); }
    protected String textOf(WebElement e) { return actions.textOf(driver, e); }
}