package org.example.hardcore.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudHomePage extends BasePage {
    public GoogleCloudHomePage(WebDriver driver) { super(driver); }

    @FindBy(name = "q") private WebElement searchInput; // site search

    public GoogleCloudHomePage open(String url) { driver.get(url); log.info("Open: %s", url); return this; }

    public SearchResultsPage search(String query) {
        type(searchInput, query);
        searchInput.sendKeys(Keys.ENTER);
        return new SearchResultsPage(driver);
    }
}
