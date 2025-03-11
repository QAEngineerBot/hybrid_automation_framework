package ui.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ui.pages.HomePage;

public class HomePageSteps extends TestBase {


    HomePage homePage = new HomePage();


    @Given("user navigate to HomePage page")
    public void userNavigateToHomePagePage () {
        homePage.verifyHomePageTitle();
    }

    @When("user should be able to view the Home Page UI with web elements")
    public void userShouldBeAbleToViewTheHomePageUIWithWebElements (DataTable dataTable){
        homePage.verifyWebElementsOnHomePageUI(dataTable);
    }

    @Then("user click on login link")
    public void userClickOnLoginLink() {
        homePage.goToLoginPage();
    }
}