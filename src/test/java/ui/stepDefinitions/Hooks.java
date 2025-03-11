package ui.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class Hooks extends TestBase {

    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void initializeTest(Scenario scenario) {
        logger.info("Initializing Test for Scenario: " + scenario.getName());
        setup(scenario); // Configure browser and test setup here
    }

    @After
    public void tearDownTest(Scenario scenario) {
        if (scenario.isFailed()) {
            logger.error("Scenario failed: " + scenario.getName());
        } else {
            logger.info("Scenario passed: " + scenario.getName());
        }
        tearDown(); // Clean up after each test
        logger.info("Test teardown complete for Scenario: " + scenario.getName());
    }

    @AfterStep
    public void afterEachStep(Scenario scenario) throws IOException {
        logger.info("Step completed for Scenario: " + scenario.getName());

        homePage.addScreenshot(scenario);
        // Capture screenshot after each step
        File screenshot = ((TakesScreenshot) homePage.getDriver()).getScreenshotAs(OutputType.FILE);
        byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
        scenario.attach(fileContent, "image/png", "Step Screenshot");
    }
}
