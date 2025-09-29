package stepDefinition;

import driver.CucumberTestRunner;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.TaskPage;

public class TaskPageStepDefinition extends CucumberTestRunner {
    @When("verify user navigates to task Page")
    public void verifyUserNavigatesToTaskPage() {
        Assert.assertTrue(appInd.clickObject(oBrowser, TaskPage.obj_TASKS_Menu));
        appInd.waitForCondition(oBrowser, TaskPage.obj_TaskPage_Header, "Text", "Customers & Projects", 5);
        Assert.assertTrue(appInd.verifyText(oBrowser, TaskPage.obj_TaskPage_Header, "Text", "Customers & Projects"));
    }

    @Then("verify add new Customer functionality and validate the same")
    public void verifyAddNewCustomerFunctionalityAndValidateTheSame(DataTable dataTable) {
        Assert.assertTrue(taskMethod.createNewCustomer(oBrowser, dataTable));
    }

    @And("verify add new project functionality and validate the same")
    public void verifyAddNewProjectFunctionalityAndValidateTheSame(DataTable dataTable) {
        Assert.assertTrue(taskMethod.createNewProject(oBrowser, dataTable));
    }

    @And("verify create new task functionality and validate the same")
    public void verifyCreateNewTaskFunctionalityAndValidateTheSame(DataTable dataTable) {
        Assert.assertTrue(taskMethod.createTask(oBrowser, dataTable));
    }

    @Then("verify delete customer functionality and validate the same")
    public void verifyDeleteCustomerFunctionalityAndValidateTheSame() {
        Assert.assertTrue(taskMethod.deleteCustomerDetails(oBrowser));
    }
}
