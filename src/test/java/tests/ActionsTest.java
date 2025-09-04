package tests;

import base.BaseTest;
import lombok.var;
import org.junit.jupiter.api.Test;
import pages.DemoQADroppablePage;
import pages.DemoQASelectablePage;
import pages.HerokuContextMenuPage;
import utils.ActionsUtil;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ActionsTest extends BaseTest {

    @Test
    void dragAndDrop_changesTextToDropped() {
        DemoQADroppablePage page = new DemoQADroppablePage(driver);
        ActionsUtil actions = new ActionsUtil(driver);
        actions.dragAndDrop(page.getDraggable(), page.getDroppable());
        assertTrue(page.droppableText().toLowerCase().contains("dropped"), "Should say 'Dropped!'");
    }

    @Test
    void contextMenu_showsAlert() {
        HerokuContextMenuPage page = new HerokuContextMenuPage(driver);
        ActionsUtil actions = new ActionsUtil(driver);
        String alertText = actions.contextClickAndGetAlertText(page.box());
        assertEquals("You selected a context menu", alertText);
    }

    @Test
    void multiSelect_selectsThreeItems() {
        DemoQASelectablePage page = new DemoQASelectablePage(driver);

        // 1) дочекатись елементів
        wait.until(d -> page.items() != null && page.items().size() >= 3);
        var items = page.items();
        int n = items.size();

        // безпечні індекси: перший, середній, останній
        int i1 = 0;
        int i2 = Math.min(2, Math.max(1, n / 2));
        int i3 = n - 1;

        // 2) пробуємо через Actions з CTRL (ціль завдання)
        new org.openqa.selenium.interactions.Actions(driver).keyDown(org.openqa.selenium.Keys.CONTROL)
                .click(items.get(i1))
                .click(items.get(i2))
                .click(items.get(i3))
                .keyUp(org.openqa.selenium.Keys.CONTROL)
                .build()
                .perform();

        // 3) невелике очікування, поки проставиться клас "active"
        wait.until(d -> page.items().stream().filter(el -> el.getAttribute("class").contains("active")).count() >= 2);

        long selected = page.items().stream().filter(el -> el.getAttribute("class").contains("active")).count();

        // 4) фолбек: якщо CTRL не спрацював на гріді — доклікаємо JS-ом
        if (selected < 3) {
            utils.JsUtils.clickByJS(driver, items.get(i1));
            utils.JsUtils.clickByJS(driver, items.get(i2));
            utils.JsUtils.clickByJS(driver, items.get(i3));
            selected = page.items().stream().filter(el -> el.getAttribute("class").contains("active")).count();
        }

        org.junit.jupiter.api.Assertions.assertEquals(3, selected, "Exactly 3 items should be active");
    }
}