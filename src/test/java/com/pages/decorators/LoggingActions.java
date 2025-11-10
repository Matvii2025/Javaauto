package com.pages.decorators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoggingActions implements UiActions {
    private static final Logger log = LogManager.getLogger(LoggingActions.class);
    private final UiActions delegate;

    public LoggingActions(UiActions delegate) { this.delegate = delegate; }

    @Override public void click(WebDriver driver, WebElement e) {
        long t = System.currentTimeMillis();
        delegate.click(driver, e);
        log.info("[ACTION] click -> {} ms", System.currentTimeMillis() - t);
    }

    @Override public void type(WebDriver driver, WebElement e, String text) {
        long t = System.currentTimeMillis();
        delegate.type(driver, e, text);
        log.info("[ACTION] type '{}' -> {} ms", text, System.currentTimeMillis() - t);
    }

    @Override public String textOf(WebDriver driver, WebElement e) {
        long t = System.currentTimeMillis();
        String v = delegate.textOf(driver, e);
        log.debug("[ACTION] textOf -> {} ms, value='{}'", System.currentTimeMillis() - t, v);
        return v;
    }
}
