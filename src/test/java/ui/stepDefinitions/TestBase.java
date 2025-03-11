package ui.stepDefinitions;

import org.testng.ITestContext;
import org.testng.Reporter;
import ui.constants.Browser;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import ui.pages.HomePage;
import ui.utility.LambdaTestUtility;
import ui.utility.LoggerUtility;

public class TestBase {
	protected HomePage homePage;
	Logger logger = LoggerUtility.getLogger(this.getClass());
	private boolean isLambdaTest;


	public void setup(Scenario scenario) {

		ITestContext context = Reporter.getCurrentTestResult().getTestContext();

		String browser = System.getProperty("browser", "chrome");

		// If not passed from command, take it from TestNG XML
		if (browser == null || browser.isEmpty()) {
			browser = context.getCurrentXmlTest().getParameter("browser");
		}

		// Default value
		if (browser == null || browser.isEmpty()) {
			browser = "chrome";
		}
		boolean isLambdaTest = Boolean.parseBoolean(System.getProperty("isLambdaTest", "false"));
		boolean isHeadless = Boolean.parseBoolean(System.getProperty("isHeadless", "false"));

		//System.out.println("Browser: " + browser);
		System.out.println("Lambda Test Enabled: " + this.isLambdaTest);
		System.out.println("Headless Mode: " + isHeadless);

		if (isLambdaTest) {
			// Initialize LambdaTest session
			WebDriver lambdaDriver = LambdaTestUtility.initializeLambdaTestSession(browser, scenario.getName());
			homePage = new HomePage(lambdaDriver);
		} else {
			// Running the test on local machine
			logger.info("Initializing local browser session for: " + browser);
			homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless);
			logger.info("HomePage initialized successfully.");
		}
	}

	public void tearDown() {
		if (isLambdaTest) {
			LambdaTestUtility.quitSession(); // Quit the LambdaTest session
		} else if (homePage != null) {
			homePage.quit(); // Close the local browser session
		} else {
			logger.warn("HomePage object is null. Skipping browser quit.");
		}
	}
}
