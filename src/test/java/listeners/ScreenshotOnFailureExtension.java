package listeners;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;
import utils.ScreenshotUtil;

import java.lang.reflect.Field;
import java.util.Optional;

public class ScreenshotOnFailureExtension implements TestWatcher {
    @Override public void testFailed(ExtensionContext ctx, Throwable cause) {
        Object test = ctx.getRequiredTestInstance();
        WebDriver d = getDriver(test);
        if (d != null) ScreenshotUtil.take(d, ctx.getDisplayName());
    }
    private WebDriver getDriver(Object test){
        try { Field f = test.getClass().getDeclaredField("driver"); f.setAccessible(true); return (WebDriver) f.get(test);}
        catch (Exception e){ return null; }
    }
    @Override public void testSuccessful(ExtensionContext c) {}
    @Override public void testDisabled(ExtensionContext c, Optional<String> r) {}
    @Override public void testAborted(ExtensionContext c, Throwable t) {}
}