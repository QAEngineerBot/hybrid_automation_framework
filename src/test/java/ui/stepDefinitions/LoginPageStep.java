package ui.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ui.pages.LoginPage;


public class LoginPageStep extends TestBase {

    LoginPage loginPage = new LoginPage();

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        loginPage.loginPageDisplayed();
    }
    @When("user enters valid {string} and {string} credentials")
    public void userEntersValidAndCredentials(String email, String password) {
        loginPage.enterCredentials(email,password);
    }

    @Then("user click login button")
    public void userAbleToLoginSuccessfully() {
       loginPage.clickLogin();

    }

    @When("user enters invalid {string} and {string} credentials")
    public void userEntersInvalidAndCredentials(String email, String password) {
        loginPage.enterCredentials(email,password);
    }

    @Then("user should see an error message")
    public void userShouldSeeAnErrorMessage() {
        loginPage.getErrorMessage();
    }
}
