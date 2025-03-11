package ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import ui.utility.BrowserUtility;

import static ui.utility.StaticConstants.LOGIN_PAGE_TITLE;

public class LoginPage extends BrowserUtility {

    private static final By EMAIL_FIELD_LOCATOR = By.xpath("//input[@id='email']");
    private static final By PASSWORD_FIELD_LOCATOR = By.xpath("//input[@id='passwd']");
    private static final By SIGN_IN_BUTTON_LOCATOR = By.xpath("//button[@id='SubmitLogin']");
    private static final By ERROR_MESSAGE_LOCATOR = By.xpath("//div[contains(@class,'alert-danger')]/ol/li");

    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    /**
     * Verifies that the Login Page is displayed by checking the title.
     */
    public void loginPageDisplayed() {
        String actualTitle = getTitle().trim();
        logger.info("Verifying the Login Page title. Expected: {}, Actual: {}", LOGIN_PAGE_TITLE, actualTitle);
        Assert.assertEquals(actualTitle, LOGIN_PAGE_TITLE, "Login Page title does not match.");
        logger.info("Login Page is displayed correctly.");
    }

    /**
     * Enters the provided email and password into the login form fields.
     *
     * @param emailAddress Email address to enter.
     * @param password     Password to enter.
     */
    public void enterCredentials(String emailAddress, String password) {
        logger.info("Entering email address: {}", emailAddress);
        enterText(EMAIL_FIELD_LOCATOR, emailAddress);

        logger.info("Entering password.");
        enterText(PASSWORD_FIELD_LOCATOR, password);
    }

    /**
     * Clicks the Login button.
     */
    public void clickLogin() {
        logger.info("Clicking on the Sign In button.");
        clickOn(SIGN_IN_BUTTON_LOCATOR);
    }

    /**
     * Retrieves and logs the error message displayed on the page.
     *
     * @return Error message text.
     */
    public String getErrorMessage() {
        logger.info("Retrieving the error message from the page.");
        String errorMessage = getVisibleText(ERROR_MESSAGE_LOCATOR);
        logger.error("Error Message: {}", errorMessage);
        return errorMessage;
    }
}
