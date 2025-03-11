package ui.utility;

import ui.constants.Env;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LambdaTestUtility {
	
    public static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";
    private static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<WebDriver>();
    private static ThreadLocal<DesiredCapabilities>  capabilitiesLocal = new ThreadLocal<DesiredCapabilities>();

    public static WebDriver initializeLambdaTestSession(String browser, String testName) {
    	
    	DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", "127");

        // Fetch accessKey and user from properties using PropertiesUtil
        String accessKey = PropertiesUtil.readProperty(Env.QA, "ACCESSKEY");
        String user = PropertiesUtil.readProperty(Env.QA, "USER");

        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("user", user);
        ltOptions.put("accessKey", accessKey);
        ltOptions.put("build", "Selenium Cucumber");
        ltOptions.put("name", testName);
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "4.23.0");
        capabilities.setCapability("LT:Options", ltOptions);
        capabilitiesLocal.set(capabilities);
        WebDriver driver = null;
        try {
			 driver = new RemoteWebDriver(new URL(HUB_URL),capabilitiesLocal.get());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        driverLocal.set(driver); 
  
    	return driverLocal.get();
    }
    public static void quitSession() {
    	if(driverLocal.get()!=null) {
    		driverLocal.get().quit();
    	}
    }
}
