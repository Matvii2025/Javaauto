package tests;

import base.BaseTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.JsUtils;

import static org.junit.jupiter.api.Assertions.*;

@Tag("regression")
public class JavaScriptExecutorTest extends BaseTest {

    @Test
    void highlight_and_jsClick_createAndShowDeleteButton() {
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        WebElement addBtn = driver.findElement(By.cssSelector("button[onclick='addElement()']"));

        JsUtils.highlight(driver, addBtn);      // 1) highlighter
        JsUtils.clickByJS(driver, addBtn);      // 2) JS-клік

        WebElement deleteBtn = driver.findElement(By.cssSelector("button.added-manually"));
        assertTrue(deleteBtn.isDisplayed(), "Delete button must be visible after JS click");
    }

    @Test
    void findByCssAndScroll_then_setValueByJS() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement user = JsUtils.findByCssAndScroll(driver, "#username"); // 3) JS finder + scroll
        JsUtils.highlight(driver, user);
        JsUtils.setValueByJS(driver, user, "tomsmith");                    // 4) JS set value

        assertEquals("tomsmith", user.getAttribute("value"));
    }
}