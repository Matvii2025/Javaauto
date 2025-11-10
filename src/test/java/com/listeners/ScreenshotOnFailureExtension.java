package com.listeners;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;
import com.utils.ScreenshotUtil;

import java.lang.reflect.Field;
import java.util.Optional;

public class ScreenshotOnFailureExtension implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext ctx, Throwable cause) {
        WebDriver driver = extractDriver(ctx);
        if (driver != null) {
            ScreenshotUtil.take(driver, ctx.getDisplayName() + "_failed");
        }
    }

    @Override
    public void testSuccessful(ExtensionContext ctx) {
        WebDriver driver = extractDriver(ctx);
        if (driver != null) {
            ScreenshotUtil.take(driver, ctx.getDisplayName() + "_passed");
        }
    }

    @Override
    public void testDisabled(ExtensionContext ctx, Optional<String> reason) { }

    @Override
    public void testAborted(ExtensionContext ctx, Throwable cause) { }

    private WebDriver extractDriver(ExtensionContext ctx) {
        try {
            Object testInstance = ctx.getRequiredTestInstance();
            Class<?> clazz = testInstance.getClass();

            while (clazz != null) {
                try {
                    Field f = clazz.getDeclaredField("driver");
                    f.setAccessible(true);
                    Object value = f.get(testInstance);
                    if (value instanceof WebDriver) {
                        return (WebDriver) value;
                    }
                    return null;
                } catch (NoSuchFieldException e) {
                    // шукаємо далі в батьківському класі
                    clazz = clazz.getSuperclass();
                }
            }
        } catch (Exception ignored) {
        }
        return null;
    }
}