package org.example.hardcore.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage extends BasePage {
    public SearchResultsPage(WebDriver driver) { super(driver); }

    @FindBy(css = "a[href*='products/calculator']") private WebElement pricingCalculatorLink;

    public PricingCalculatorPage openCalculator() {
        click(pricingCalculatorLink);
        return new PricingCalculatorPage(driver);
    }
}