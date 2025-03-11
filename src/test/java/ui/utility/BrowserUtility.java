package ui.utility;

import ui.constants.Browser;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class BrowserUtility {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    Logger logger = LoggerUtility.getLogger(this.getClass());
    WebDriverWait webDriverWait;

    public BrowserUtility() {
    }

    public WebDriver getDriver() {
        try {
            return driver.get();
        } catch (Exception e) {
            logger.error("Error in getDriver(): " + e.getMessage());
            return null;
        }
    }

    public BrowserUtility(WebDriver driver) {
        try {
            this.driver.set(driver);
        } catch (Exception e) {
            logger.error("Error in BrowserUtility(WebDriver driver): " + e.getMessage());
        }
    }

    public BrowserUtility(Browser browserName) {
        try {
            logger.info("Launching Browser for " + browserName);
            switch (browserName) {
                case CHROME -> driver.set(new ChromeDriver());
                case EDGE -> driver.set(new EdgeDriver());
                case FIREFOX -> driver.set(new FirefoxDriver());
                default -> {
                    logger.error("Invalid Browser Name... Please select valid option");
                    System.err.println("Invalid Browser Name... Please select valid option");
                }
            }
        } catch (Exception e) {
            logger.error("Error in BrowserUtility(Browser browserName): " + e.getMessage());
        }
    }

    public BrowserUtility(Browser browserName, boolean isHeadless) {
        try {
            logger.info("Launching Browser for " + browserName);
            if (browserName == Browser.CHROME) {
                ChromeOptions options = new ChromeOptions();
                if (isHeadless) {
                    options.addArguments("--headless", "--window-size=1920,1800");
                }
                driver.set(new ChromeDriver(options));
            } else if (browserName == Browser.EDGE) {
                EdgeOptions options = new EdgeOptions();
                if (isHeadless) {
                    options.addArguments("--headless", "--window-size=1920,1800");
                }
                driver.set(new EdgeDriver(options));
            } else if (browserName == Browser.FIREFOX) {
                FirefoxOptions options = new FirefoxOptions();
                if (isHeadless) {
                    options.addArguments("--headless", "--window-size=1920,1800");
                }
                driver.set(new FirefoxDriver(options));
            }
        } catch (Exception e) {
            logger.error("Error in BrowserUtility(Browser browserName, boolean isHeadless): " + e.getMessage());
        }
    }

    public void goToWebsite(String url) {
        try {
            logger.info("Visiting the website: " + url);
            driver.get().get(url);
        } catch (Exception e) {
            logger.error("Error in goToWebsite(): " + e.getMessage());
        }
    }

    public String getTitle() {
        try {
            return driver.get().getTitle();
        } catch (Exception e) {
            logger.error("Error in getTitle(): " + e.getMessage());
            return "";
        }
    }

    public void assertEquals(String expectedVal, String actualVal, String fieldName) {
        try {
            Assert.assertEquals(actualVal, expectedVal);
            System.out.println("Text verification successful on " + fieldName);
        } catch (Exception e) {
            logger.error("Text verification failed on " + fieldName + ": " + e.getMessage());
        }
    }

    public boolean isElementDisplayed(By locator) {
        try {
            return !driver.get().findElements(locator).isEmpty();
        } catch (Exception e) {
            logger.error("Error in isElementDisplayed(): " + e.getMessage());
            return false;
        }
    }

    public void maximizeWindow() {
        try {
            driver.get().manage().window().maximize();
        } catch (Exception e) {
            logger.error("Error in maximizeWindow(): " + e.getMessage());
        }
    }

    public void clickOn(By locator) {
        try {
            driver.get().findElement(locator).click();
        } catch (Exception e) {
            logger.error("Error in clickOn(): " + e.getMessage());
        }
    }

    public void enterText(By locator, String textToEnter) {
        try {
            driver.get().findElement(locator).sendKeys(textToEnter);
        } catch (Exception e) {
            logger.error("Error in enterText(): " + e.getMessage());
        }
    }

    public void clearText(By textBoxLocator) {
        try {
            driver.get().findElement(textBoxLocator).clear();
        } catch (Exception e) {
            logger.error("Error in clearText(): " + e.getMessage());
        }
    }

    public void selectFromDropDown(By dropDownLocator, String optionToSelect) {
        try {
            new Select(driver.get().findElement(dropDownLocator)).selectByVisibleText(optionToSelect);
        } catch (Exception e) {
            logger.error("Error in selectFromDropDown(): " + e.getMessage());
        }
    }

    public void enterSpecialKey(By locator, Keys keyToEnter) {
        try {
            driver.get().findElement(locator).sendKeys(keyToEnter);
        } catch (Exception e) {
            logger.error("Error in enterSpecialKey(): " + e.getMessage());
        }
    }

    public String getVisibleText(By locator) {
        try {
            return driver.get().findElement(locator).getText();
        } catch (Exception e) {
            logger.error("Error in getVisibleText(): " + e.getMessage());
            return "";
        }
    }

    public String getVisibleText(WebElement element) {
        try {
            return element.getText();
        } catch (Exception e) {
            logger.error("Error in getVisibleText(WebElement element): " + e.getMessage());
            return "";
        }
    }

    public List<String> getAllVisibleText(By locator) {
        try {
            List<WebElement> elements = driver.get().findElements(locator);
            List<String> visibleTextList = new ArrayList<>();
            for (WebElement element : elements) {
                visibleTextList.add(getVisibleText(element));
            }
            return visibleTextList;
        } catch (Exception e) {
            logger.error("Error in getAllVisibleText(): " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            return !driver.get().findElements(locator).isEmpty();
        } catch (Exception e) {
            logger.error("Error in isElementPresent(): " + e.getMessage());
            return false;
        }
    }

    public void waitVisibilityOfElement(By locator) {
        try {
            webDriverWait = new WebDriverWait(driver.get(), Duration.ofSeconds(StaticConstants.ELEMENT_VISIBILITY_TIME));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Error in waitVisibilityOfElement(): " + e.getMessage());
        }
    }

    public void addScreenshot(Scenario scenario) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver.get();
            byte[] fileContent = FileUtils.readFileToByteArray(screenshot.getScreenshotAs(OutputType.FILE));
            scenario.attach(fileContent, "image/png", "Step Screenshot");
        } catch (Exception e) {
            logger.error("Error in addScreenshot(): " + e.getMessage());
        }
    }

    public String takeScreenShot(String name) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver.get();
            File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
            String path = "./screenshots/" + name + "-" + new SimpleDateFormat("HH-mm-ss").format(new Date()) + ".png";
            FileUtils.copyFile(screenshotData, new File(path));
            return path;
        } catch (Exception e) {
            logger.error("Error in takeScreenShot(): " + e.getMessage());
            return "";
        }
    }

    public void quit() {
        try {
            driver.get().quit();
        } catch (Exception e) {
            logger.error("Error in quit(): " + e.getMessage());
        }
    }
}

