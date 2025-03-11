package ui.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        // Path to your feature files
        features = {"src/test/resources/ui/features"},
        // Package containing step definitions
        glue = {"ui/stepDefinitions"}, plugin = {
        // For readable console output
        "pretty",
        // HTML report
        "html:target/cucumber-reports/cucumber.html",
        // JSON report
        "json:target/cucumber-reports/cucumber.json",
        // To rerun failed tests
        "rerun:target/rerun.txt", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        // Tags to filter scenarios
        tags = "",
        // Better console output formatting
        monochrome = true, publish = true)
public class RunCucumberTest extends AbstractTestNGCucumberTests {


}
