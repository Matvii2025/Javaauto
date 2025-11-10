package com.tests;

import com.base.BaseTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.pages.DemoQADroppablePage;
import com.pages.DemoQASelectablePage;
import com.pages.HerokuContextMenuPage;
import com.utils.ActionsUtil;
import com.utils.ScreenshotUtil;

import static org.junit.jupiter.api.Assertions.*;

@Tag("regression")
public class ActionsTest extends BaseTest {

    @Test
    void dragAndDrop_changesTextToDropped() {
        DemoQADroppablePage page = new DemoQADroppablePage(driver);
        ActionsUtil actions = new ActionsUtil(driver);
        actions.dragAndDrop(page.getDraggable(), page.getDroppable());
        assertTrue(page.droppableText().toLowerCase().contains("dropped"), "Should say 'Dropped!'");
        ScreenshotUtil.take(driver, "contextMenu_showsAlert");
    }

    @Test
    void contextMenu_showsAlert() {
        HerokuContextMenuPage page = new HerokuContextMenuPage(driver);
        ActionsUtil actions = new ActionsUtil(driver);
        String alertText = actions.contextClickAndGetAlertText(page.box());
        assertEquals("You selected a context menu", alertText);
        ScreenshotUtil.take(driver, "contextMenu_showsAlert");
    }

    @Test
    void multiSelect_selectsThreeItems() {
        DemoQASelectablePage page = new DemoQASelectablePage(driver);

        var localWait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(20));

        localWait.until(d -> page.items() != null && page.items().size() >= 3);

        int size = page.items().size();
        int i1 = 0;
        int i2 = Math.min(Math.max(1, size / 2), size - 2);
        int i3 = size - 1;
        int[] idx = { i1, i2, i3 };

        for (int i : idx) {
            org.openqa.selenium.WebElement el = page.items().get(i);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});", el);

            new org.openqa.selenium.interactions.Actions(driver).keyDown(org.openqa.selenium.Keys.CONTROL)
                    .click(el)
                    .keyUp(org.openqa.selenium.Keys.CONTROL)
                    .pause(java.time.Duration.ofMillis(200))
                    .perform();
        }

        for (int attempt = 0; attempt < 2; attempt++) {
            long activeNow = page.items().stream().filter(e ->
            {
                String c = e.getAttribute("class");
                return c != null && c.contains("active");
            }).count();
            if (activeNow >= 3)
                break;

            for (int i : idx) {
                org.openqa.selenium.WebElement el = page.items().get(i);
                new org.openqa.selenium.interactions.Actions(driver).keyDown(org.openqa.selenium.Keys.CONTROL)
                        .click(el)
                        .keyUp(org.openqa.selenium.Keys.CONTROL)
                        .pause(java.time.Duration.ofMillis(150))
                        .perform();
            }
        }

        localWait.until(d -> page.items().stream().filter(el ->
        {
            String cls = el.getAttribute("class");
            return cls != null && cls.contains("active");
        }).count() == 3);

        long selected = page.items().stream().filter(el ->
        {
            String cls = el.getAttribute("class");
            return cls != null && cls.contains("active");
        }).count();
        org.junit.jupiter.api.Assertions.assertEquals(3, selected, "Exactly 3 items should be active");
        ScreenshotUtil.take(driver, "contextMenu_showsAlert");
    }
}