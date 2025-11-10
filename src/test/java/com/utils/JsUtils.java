package com.utils;

import org.openqa.selenium.*;
public class JsUtils {
    private JsUtils() {}

    public static void highlight(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String original = element.getAttribute("style");
        try {
            for (int i = 0; i < 2; i++) {
                js.executeScript(
                        "arguments[0].setAttribute('style', arguments[1]);",
                        element, "outline:3px solid red; outline-offset:2px; transition: outline 0.2s ease;");
                try { Thread.sleep(120); } catch (InterruptedException ignored) {}
                js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, original);
                try { Thread.sleep(80); } catch (InterruptedException ignored) {}
            }
        } finally {
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, original);
        }
    }

    public static void clickByJS(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public static WebElement findByCssAndScroll(WebDriver driver, String css) {
        Object res = ((JavascriptExecutor) driver).executeScript(
                "var el = document.querySelector(arguments[0]);" +
                        "if (el) el.scrollIntoView({block:'center'});" +
                        "return el;", css);
        if (res instanceof WebElement) return (WebElement) res;
        throw new NoSuchElementException("No element for css: " + css);
    }

    public static void setValueByJS(WebDriver driver, WebElement element, String value) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input', {bubbles:true}));" +
                        "arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
                element, value);
    }
}
