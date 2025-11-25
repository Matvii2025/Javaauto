package com.cucumber.steps;

import com.cucumber.hooks.Hooks;
import com.utils.JsUtils;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class JavaScriptSteps {

    private WebElement element;

    @Given("User is on the Add Remove Elements page")
    public void openAddRemovePage() {
        Hooks.driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
    }

    @Given("User opens the Login page for JS test")
    public void openLoginPageForJs() {
        Hooks.driver.get("https://the-internet.herokuapp.com/login");
    }

    @When("User highlights the Add Element button")
    public void highlightAddBtn() {
        WebElement addBtn = Hooks.driver.findElement(By.cssSelector("button[onclick='addElement()']"));
        JsUtils.highlight(Hooks.driver, addBtn);
    }

    @When("User clicks the Add Element button using JS")
    public void clickAddBtnJS() {
        WebElement addBtn = Hooks.driver.findElement(By.cssSelector("button[onclick='addElement()']"));
        JsUtils.clickByJS(Hooks.driver, addBtn);
    }

    @Then("Delete button should be visible")
    public void verifyDeleteButtonVisible() {
        WebElement deleteBtn = Hooks.driver.findElement(By.cssSelector("button.added-manually"));
        Assert.assertTrue("Delete button is NOT visible!", deleteBtn.isDisplayed());
    }

    @When("User finds input {string} by JS and scrolls into view")
    public void findInputByJs(String selector) {
        element = JsUtils.findByCssAndScroll(Hooks.driver, selector);
    }

    @When("User highlights the element")
    public void highlightElement() {
        JsUtils.highlight(Hooks.driver, element);
    }

    @When("User sets JS value {string} into the element")
    public void setJsValue(String value) {
        JsUtils.setValueByJS(Hooks.driver, element, value);
    }

    @Then("Element should contain value {string}")
    public void verifyInputValue(String expected) {
        Assert.assertEquals("Input value is incorrect!", expected, element.getAttribute("value"));
    }
}