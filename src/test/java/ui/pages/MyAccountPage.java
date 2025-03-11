package ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import ui.utility.BrowserUtility;
import ui.utility.LoggerUtility;

public class MyAccountPage extends BrowserUtility {

    private static final Logger logger = LoggerUtility.getLogger(MyAccountPage.class);
    private static final By USER_NAME_LOCATOR = By.xpath("//a[@title=\"View my customer account\"]/span");

    /**
     * Verifies if the user's name is displayed on the My Account page.
     */
    public void isUserNamePresent() {
        logger.info("Checking if the user name is displayed on the My Account page.");
        boolean isDisplayed = isElementDisplayed(USER_NAME_LOCATOR);
        if (isDisplayed) {
            logger.info("User name is displayed successfully.");
        } else {
            logger.error("User name is not displayed.");
        }
    }
}
