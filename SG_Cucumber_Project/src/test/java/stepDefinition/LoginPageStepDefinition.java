package stepDefinition;

import driver.CucumberTestRunner;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class LoginPageStepDefinition extends CucumberTestRunner {
    @Given("verify url {string} is loaded successful")
    public void verifyUrlIsLoadedSuccessful(String strURL) {
        String url = propData.get(strURL);
        Assert.assertTrue(loginMethod.navigateURL(oBrowser, url));
    }

    @Then("verify login to application is successful")
    public void verifyLoginToApplicationIsSuccessful(DataTable dataTable) {
        Assert.assertTrue(loginMethod.loginToApplication(oBrowser, dataTable));
    }

    @And("verify logout from application is successful")
    public void verifyLogoutFromApplicationIsSuccessful() {
        Assert.assertTrue(loginMethod.logoutFromApplication(oBrowser));
    }
}
