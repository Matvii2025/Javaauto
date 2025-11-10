package com.utils;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {

    private static final Logger LOG = LogManager.getLogger(ScreenshotUtil.class);

    private static final DateTimeFormatter DATE_DIR =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TS =
            DateTimeFormatter.ofPattern("HH-mm-ss_SSS");

    public static void take(WebDriver driver, String name) {
        try {
            if (!(driver instanceof TakesScreenshot)) {
                LOG.warn("Driver does not support screenshots: {}", driver.getClass().getName());
                return;
            }

            byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            Path folder = Paths.get("target", "screenshots",
                    LocalDate.now().format(DATE_DIR));
            Files.createDirectories(folder);

            String safeName = sanitize(name) + "_" +
                    LocalDateTime.now().format(TS) + ".png";

            Path dest = folder.resolve(safeName);
            Files.write(dest, bytes,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);

            LOG.info("Screenshot saved: {}", dest.toAbsolutePath());

            Allure.addAttachment(
                    "Screenshot - " + safeName,
                    "image/png",
                    new ByteArrayInputStream(bytes),
                    ".png"
            );

        } catch (Exception e) {
            LOG.error("Failed to create screenshot", e);
        }
    }

    private static String sanitize(String name) {
        return name == null ? "screenshot"
                : name.replaceAll("[^a-zA-Z0-9._-]", "_");
    }
}