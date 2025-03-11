package ui.listeners;

import ui.constants.Env;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import ui.utility.PropertiesUtil;

public class MyRetryAnalyzer implements IRetryAnalyzer {

	private static final int MAX_NUMBER_OF_ATTEMPTS = Integer.parseInt(PropertiesUtil.readProperty(Env.QA, "MAX_NUMBER_OF_ATTEMPTS"));
	private static int currentAttempt=1;
	@Override
	public boolean retry(ITestResult result) {
		
		if(currentAttempt<=MAX_NUMBER_OF_ATTEMPTS) {
			currentAttempt++;
			return true;
		}
		
		return false;
	}

}
