package com.utils;

import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {
    private static final Logger LOG = LogManager.getLogger(ScreenshotUtil.class);

    private static final DateTimeFormatter DATE_DIR = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TS = DateTimeFormatter.ofPattern("HH-mm-ss_SSS");

    public static Path takeAndAttach(WebDriver driver, String testName) {
        if (!(driver instanceof TakesScreenshot)) {
            LOG.warn("Driver does not support screenshots: {}", driver.getClass().getName());
            return null;
        }

        try {
            byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            Path folder = Paths.get("target", "screenshots", LocalDate.now().format(DATE_DIR));
            Files.createDirectories(folder);

            String safeName = sanitize(testName) + "_" + LocalDateTime.now().format(TS) + ".png";
            Path dest = folder.resolve(safeName);

            Files.write(dest, bytes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            attachToAllure(bytes);

            LOG.info("Screenshot saved: {}", dest.toAbsolutePath());
            return dest;
        } catch (IOException e) {
            LOG.error("Failed to save screenshot", e);
            return null;
        } catch (Exception e) {
            LOG.error("Unexpected error during screenshot creation", e);
            return null;
        }
    }

    public static Path take(WebDriver driver, String testName) {
        return takeAndAttach(driver, testName);
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private static byte[] attachToAllure(byte[] bytes) {
        return bytes;
    }

    private static String sanitize(String name) {
        return (name == null || name.isBlank())
                ? "screenshot"
                : name.replaceAll("[^a-zA-Z0-9._-]", "_");
    }
}