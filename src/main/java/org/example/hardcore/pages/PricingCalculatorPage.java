package org.example.hardcore.pages;

import org.example.hardcore.model.Estimate;
import org.example.hardcore.util.Waits;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class PricingCalculatorPage extends BasePage implements CalculatorActions {
    public PricingCalculatorPage(WebDriver driver) { super(driver); }

    @FindBy(css = "iframe[name*='goog_']") private WebElement outerFrame;
    @FindBy(css = "iframe#iframe")         private WebElement innerFrame;

    @FindBy(id = "input_100")               private WebElement numberOfInstances;
    @FindBy(id = "select_value_label_49")   private WebElement osDropdown;
    @FindBy(id = "select_value_label_50")   private WebElement machineClassDropdown;
    @FindBy(id = "select_value_label_52")   private WebElement machineTypeDropdown;
    @FindBy(xpath = "//md-checkbox[@aria-label='Add GPUs']") private WebElement addGpuCheckbox;
    @FindBy(id = "select_value_label_415")  private WebElement gpuTypeDropdown;
    @FindBy(id = "select_value_label_416")  private WebElement gpuCountDropdown;
    @FindBy(id = "select_value_label_356")  private WebElement localSsdDropdown;
    @FindBy(id = "select_value_label_57")   private WebElement datacenterDropdown;
    @FindBy(id = "select_value_label_58")   private WebElement committedUsageDropdown;
    @FindBy(xpath = "//button[@aria-label='Add to Estimate']") private WebElement addToEstimateBtn;

    @FindBy(xpath = "//h2[contains(.,'Estimate')]//following::b[contains(.,'Total Estimated Cost')]")
    private WebElement totalEstimatedCost;
    @FindBy(xpath = "//button[@id='email_quote']") private WebElement emailEstimateBtn;
    @FindBy(xpath = "//input[@type='email']")      private WebElement emailField;
    @FindBy(xpath = "//button[contains(.,'Send Email')]") private WebElement sendEmailBtn;

    private void switchToCalculator() {
        driver.switchTo().defaultContent();
        Waits.waitVisible(driver, outerFrame);
        driver.switchTo().frame(outerFrame);
        Waits.waitVisible(driver, innerFrame);
        driver.switchTo().frame(innerFrame);
        log.debug("Switched into calculator iframes");
    }

    private void selectFromDropdown(WebElement dropdown, String visibleOption) {
        click(dropdown);
        WebElement option = Waits.waitVisible(driver,
                driver.findElement(By.xpath(String.format("//md-option/div[contains(text(),'%s')]", visibleOption))));
        option.click();
    }

    @Override
    public PricingCalculatorPage fill(Estimate e) {
        switchToCalculator();
        type(numberOfInstances, String.valueOf(e.instances));
        selectFromDropdown(osDropdown, e.operatingSystem);
        selectFromDropdown(machineClassDropdown, e.machineClass);
        selectFromDropdown(machineTypeDropdown, e.machineType);
        click(addGpuCheckbox);
        selectFromDropdown(gpuTypeDropdown, e.gpuType);
        selectFromDropdown(gpuCountDropdown, String.valueOf(e.gpuCount));
        selectFromDropdown(localSsdDropdown, e.ssd);
        selectFromDropdown(datacenterDropdown, e.datacenter);
        selectFromDropdown(committedUsageDropdown, e.committedUseYears + " Year");
        click(addToEstimateBtn);
        return this;
    }

    @Override
    public String getTotalCostText() {
        switchToCalculator();
        return textOf(totalEstimatedCost);
    }

    @Override
    public PricingCalculatorPage emailEstimate(String email) {
        switchToCalculator();
        click(emailEstimateBtn);
        type(emailField, email);
        click(sendEmailBtn);
        log.action("Estimate emailed to %s", email);
        return this;
    }
}