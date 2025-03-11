package ui.stepDefinitions;

import io.cucumber.java.en.And;
import ui.pages.MyAccountPage;

public class MyAccountPageStep extends TestBase {
    MyAccountPage myAccountPage = new MyAccountPage();

    @And("user able to see user name on my account page")
    public void userAbleToSeeUserNameOnMyAccountPage() {
            myAccountPage.isUserNamePresent();
    }
}
