package com.cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.cucumber.steps", "com.cucumber.hooks"},
        plugin = {"pretty", "html:target/cucumber-report.html"}
)
public class CucumberTest {
}