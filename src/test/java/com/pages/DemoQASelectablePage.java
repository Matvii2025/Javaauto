package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class DemoQASelectablePage {
    private final WebDriver driver;

    @FindBy(css = "#verticalListContainer li")
    private List<WebElement> items;

    public DemoQASelectablePage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://demoqa.com/selectable");
        PageFactory.initElements(driver, this);
    }

    public java.util.List<WebElement> items() { return items; }
}