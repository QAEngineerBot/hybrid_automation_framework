package ui.pages;

import ui.constants.Browser;
import io.cucumber.datatable.DataTable;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ui.utility.BrowserUtility;
import ui.utility.JSONUtility;
import ui.utility.LoggerUtility;

import java.util.List;

import static ui.constants.Env.QA;
import static ui.utility.StaticConstants.HOME_PAGE_TITLE;

public final class HomePage extends BrowserUtility {

    private static final Logger logger = LoggerUtility.getLogger(HomePage.class);

    private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(), 'Sign in')]");
    private static final By homePageSearchTextBox = By.xpath("//input[@id='search_query_top']");
    private static final By homePageViewMyCart = By.xpath("//a[@title='View my shopping cart']");
    private static final By homePageContactUsLink = By.xpath("//a[@title='Contact us']");
    private static final By homePageLoginLink = By.xpath("//a[@title='Log in to your customer account']");
    private static final By homePageLogo = By.xpath("//img[@alt='My Shop']");

    public HomePage(Browser browserName, boolean isHeadless) {
        super(browserName, isHeadless);
        logger.info("Navigating to website using browser: {} in headless mode: {}", browserName, isHeadless);
        goToWebsite(JSONUtility.readJSON(QA));
    }

    public HomePage(WebDriver driver) {
        super(driver);
        logger.info("Initializing HomePage with provided WebDriver.");
        goToWebsite(JSONUtility.readJSON(QA));
    }

    public HomePage() {
        super();
        logger.info("Initializing HomePage with default WebDriver settings.");
    }

    /**
     * Verifies that the home page title matches the expected title.
     */
    public void verifyHomePageTitle() {
        logger.info("Verifying the home page title.");
        waitVisibilityOfElement(homePageLogo);
        String actualTitle = getTitle().trim();
        logger.info("Expected Title: {}, Actual Title: {}", HOME_PAGE_TITLE, actualTitle);
        Assert.assertEquals(actualTitle, HOME_PAGE_TITLE, "Home Page title does not match.");
        logger.info("Home Page title verification successful.");
    }

    /**
     * Verifies the presence of web elements on the home page based on the provided DataTable.
     *
     * @param dataTable DataTable containing the names of elements to verify.
     */
    public void verifyWebElementsOnHomePageUI(DataTable dataTable) {
        List<String> elements = dataTable.asList();
        logger.info("Verifying the presence of the following elements on the home page: {}", elements);

        for (String element : elements) {
            switch (element) {
                case "Home Page Logo":
                    logger.info("Checking if 'Home Page Logo' is displayed.");
                    Assert.assertTrue(isElementDisplayed(homePageLogo), "'Home Page Logo' is not displayed.");
                    logger.info("'Home Page Logo' is present.");
                    break;

                case "View My Cart":
                    logger.info("Checking if 'View My Cart' is displayed.");
                    Assert.assertTrue(isElementDisplayed(homePageViewMyCart), "'View My Cart' is not displayed.");
                    logger.info("'View My Cart' is present.");
                    break;

                case "Search Text Box":
                    logger.info("Checking if 'Search Text Box' is displayed.");
                    Assert.assertTrue(isElementDisplayed(homePageSearchTextBox), "'Search Text Box' is not displayed.");
                    logger.info("'Search Text Box' is present.");
                    break;

                case "Login Link":
                    logger.info("Checking if 'Login Link' is displayed.");
                    Assert.assertTrue(isElementDisplayed(homePageLoginLink), "'Login Link' is not displayed.");
                    logger.info("'Login Link' is present.");
                    break;

                case "Contact Us Link":
                    logger.info("Checking if 'Contact Us Link' is displayed.");
                    Assert.assertTrue(isElementDisplayed(homePageContactUsLink), "'Contact Us Link' is not displayed.");
                    logger.info("'Contact Us Link' is present.");
                    break;

                default:
                    logger.error("Unexpected element: {}", element);
                    throw new IllegalArgumentException("Unexpected value: " + element);
            }
        }
    }

    /**
     * Navigates to the login page by clicking the sign-in link.
     *
     * @return A new instance of the LoginPage.
     */
    public LoginPage goToLoginPage() {
        logger.info("Navigating to the Login Page by clicking on the 'Sign In' link.");
        clickOn(SIGN_IN_LINK_LOCATOR);
        logger.info("Navigation to Login Page successful.");
        return new LoginPage();
    }
}
