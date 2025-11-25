package com.cucumber.steps;

import com.cucumber.hooks.Hooks;
import com.pages.DemoQADroppablePage;
import com.pages.DemoQASelectablePage;
import com.pages.HerokuContextMenuPage;
import com.utils.ActionsUtil;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionsSteps {

    DemoQADroppablePage dropPage;
    HerokuContextMenuPage contextPage;
    DemoQASelectablePage selectablePage;
    ActionsUtil actions;

    @Given("User navigates to Drag and Drop page")
    public void openDragPage() {
        dropPage = new DemoQADroppablePage(Hooks.driver); // page loads inside constructor
        actions = new ActionsUtil(Hooks.driver);
    }

    @When("User drags draggable element onto droppable area")
    public void dragAndDrop() {
        actions.dragAndDrop(dropPage.getDraggable(), dropPage.getDroppable());
    }

    @Then("Droppable text should contain {string}")
    public void verifyDropText(String expected) {
        String actual = dropPage.droppableText().toLowerCase();
        Assert.assertTrue(actual.contains(expected.toLowerCase()));
    }

    @Given("User navigates to Context Menu page")
    public void openContextMenu() {
        contextPage = new HerokuContextMenuPage(Hooks.driver); // loads page in constructor
        actions = new ActionsUtil(Hooks.driver);
    }
    @Given("User opens the application homepage")
    public void userOpensHomepage() {
        // Головна сторінка може бути будь-якою. Зазвичай — головне доменне посилання.
        Hooks.driver.get("https://demoqa.com");
    }

    @When("User right-clicks the context menu box")
    public void rightClickBox() {
        // We only have contextClickAndGetAlertText(), not pure contextClick()
        actions.contextClickAndGetAlertText(contextPage.box());
    }

    @Then("Alert text should be {string}")
    public void verifyAlert(String expected) {
        String alertText = actions.contextClickAndGetAlertText(contextPage.box());
        Assert.assertEquals(expected, alertText);
    }

    @Given("User navigates to Selectable page")
    public void openSelectablePage() {
        selectablePage = new DemoQASelectablePage(Hooks.driver); // page loads in constructor
    }

    @When("User selects items with indexes {int}, {int}, {int}")
    public void selectItems(int i1, int i2, int i3) {

        var wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(20));
        wait.until(d -> selectablePage.items() != null && selectablePage.items().size() >= 3);

        int[] idx = new int[]{i1, i2, i3};

        for (int i : idx) {
            var el = selectablePage.items().get(i);

            ((JavascriptExecutor) Hooks.driver)
                    .executeScript("arguments[0].scrollIntoView({block:'center'});", el);

            new Actions(Hooks.driver)
                    .keyDown(Keys.CONTROL)
                    .click(el)
                    .keyUp(Keys.CONTROL)
                    .pause(Duration.ofMillis(200))
                    .perform();
        }
    }

    @Then("Exactly {int} items should be selected")
    public void verifySelectedCount(int expected) {

        var wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(10));
        wait.until(d -> selectablePage.items().stream()
                .filter(e -> {
                    String cls = e.getAttribute("class");
                    return cls != null && cls.contains("active");
                }).count() == expected);

        long count = selectablePage.items().stream()
                .filter(e -> {
                    String cls = e.getAttribute("class");
                    return cls != null && cls.contains("active");
                }).count();

        Assert.assertEquals(expected, count);
    }
}