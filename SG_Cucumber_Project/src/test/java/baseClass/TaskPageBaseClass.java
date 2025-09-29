package baseClass;

import driver.CucumberTestRunner;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import pages.TaskPage;
import java.util.List;
import java.util.Map;

public class TaskPageBaseClass extends CucumberTestRunner {
    /************************************************************************
     * Method            : createNewCustomer()
     * Purpose           :
     * Parameters        :
     * Return Type       :
     * Author Name      : Test user
     *************************************************************************/
    public boolean createNewCustomer(WebDriver oBrowser, DataTable dataTable){
        List<Map<String, String>> inputData = null;
        try{
            inputData = dataTable.asMaps(String.class, String.class);
            appInd.waitForCondition(oBrowser, TaskPage.obj_AddNew_Button, "Clickable", "", 5);
            Assert.assertTrue(appInd.clickObject(oBrowser, TaskPage.obj_AddNew_Button));
            Assert.assertTrue(appInd.clickObject(oBrowser, TaskPage.obj_NewCustomer_Button));
            customerName = inputData.get(0).get("CustomerName") + appInd.getDateTime("hhmmss");
            appInd.waitForCondition(oBrowser, TaskPage.obj_EnterCustomerName_Edit, "clickable", "", 5);

            Assert.assertTrue(appInd.setObject(oBrowser, TaskPage.obj_EnterCustomerName_Edit, customerName));
            Assert.assertTrue(appInd.setObject(oBrowser, TaskPage.obj_AddCustomerDesc_TextArea, inputData.get(0).get("CustomerDesc")));
            report.writeResult(oBrowser, "Screenshot", "After entering the customer details");
            Assert.assertTrue(appInd.clickObject(oBrowser, TaskPage.obj_CreateCustomer_Button));
            appInd.waitForCondition(oBrowser, By.xpath(String.format(TaskPage.obj_CustomerName_Label, customerName)), "Clickable", "", 5);
            Assert.assertTrue(appInd.verifyText(oBrowser, By.xpath(String.format(TaskPage.obj_CustomerName_Label, customerName)), "Text", customerName));
            report.writeResult(oBrowser, "Screenshot", "After creating the new customer");
            return true;
        }catch(Exception e){
            report.writeResult(oBrowser, "Exception", "Exception in the 'createNewCustomer()' method. "+ e);
            return false;
        }
    }


    /************************************************************************
     * Method            : createNewProject()
     * Purpose           :
     * Parameters        :
     * Return Type       :
     * Author Name      : Test user
     *************************************************************************/
    public boolean createNewProject(WebDriver oBrowser, DataTable dataTable){
        List<Map<String, String>> inputData = null;
        try{
            inputData = dataTable.asMaps(String.class, String.class);
            appInd.waitForCondition(oBrowser, TaskPage.obj_AddNew_Button, "Clickable", "", 5);
            Assert.assertTrue(appInd.clickObject(oBrowser, TaskPage.obj_AddNew_Button));
            Assert.assertTrue(appInd.clickObject(oBrowser, TaskPage.obj_NewProject_Button));
            projectName = inputData.get(0).get("ProjectName") + appInd.getDateTime("hhmmss");
            appInd.waitForCondition(oBrowser, TaskPage.obj_EnterProjectName_Edit, "clickable", "", 5);

            Assert.assertTrue(appInd.setObject(oBrowser, TaskPage.obj_EnterProjectName_Edit, projectName));

            //Select the customer name
            Assert.assertTrue(appInd.clickObject(oBrowser, TaskPage.obj_Customer_Dropdown));
            Assert.assertTrue(appInd.clickObject(oBrowser, By.xpath(String.format(TaskPage.obj_Customer_Dropdown_Item, customerName))));

            Assert.assertTrue(appInd.setObject(oBrowser, TaskPage.obj_AddProjectDescription_TextArea, inputData.get(0).get("ProjectDes")));
            report.writeResult(oBrowser, "Screenshot", "After entering the new project details");
            Assert.assertTrue(appInd.clickObject(oBrowser, TaskPage.obj_CreateProject_Button));
            appInd.waitForCondition(oBrowser, By.xpath(String.format(TaskPage.obj_ProjectName_Label, projectName)), "Clickable", "", 5);
            Assert.assertTrue(appInd.verifyText(oBrowser, By.xpath(String.format(TaskPage.obj_ProjectName_Label, projectName)), "Text", projectName));
            report.writeResult(oBrowser, "Screenshot", "After creating the new Project");
            return true;
        }catch(Exception e){
            report.writeResult(oBrowser, "Exception", "Exception in the 'createNewProject()' method. "+ e);
            return false;
        }
    }


    /************************************************************************
     * Method            : createTask()
     * Purpose           :
     * Parameters        :
     * Return Type       :
     * Author Name      :
     *************************************************************************/
    public boolean createTask(WebDriver oBrowser, DataTable dataTable){
        String arrTaskDetails[];
        String arrTaskData[];
        List<Map<String, String>> inputData = null;
        try{
            inputData = dataTable.asMaps(String.class, String.class);
            appInd.waitForCondition(oBrowser, TaskPage.obj_AddNewTask_Button, "Clickable", "", 5);
            Assert.assertTrue(appInd.clickObject(oBrowser, TaskPage.obj_AddNewTask_Button));
            Assert.assertTrue(appInd.clickObject(oBrowser, TaskPage.obj_CreateNewTask_Button));
            appInd.waitForCondition(oBrowser, TaskPage.obj_CustomerName_Dropdown, "Clickable", "", 5);
            Assert.assertTrue(appInd.clickObject(oBrowser, TaskPage.obj_CustomerName_Dropdown));
            Assert.assertTrue(appInd.clickObject(oBrowser, By.xpath(String.format(TaskPage.obj_customerProject_Label, customerName))));
            Assert.assertTrue(appInd.clickObject(oBrowser, TaskPage.obj_ProjectName_Dropdown));
            Assert.assertTrue(appInd.clickObject(oBrowser, By.xpath(String.format(TaskPage.obj_customerProject_Label, projectName))));

            //Enter the task description
            arrTaskDetails = inputData.get(0).get("taskDetails").split("%", -1);
            for(int i=0; i<arrTaskDetails.length; i++){
                arrTaskData = arrTaskDetails[i].split("#", -1);
                Assert.assertTrue(appInd.setObject(oBrowser, By.xpath("("+TaskPage.obj_TaskName_Edit+")["+(i+1)+"]"), arrTaskData[0]));

                //Edit task description
                if(!arrTaskData[1].isEmpty()){
                    Assert.assertTrue(appInd.clickObject(oBrowser, By.xpath("("+TaskPage.obj_TaskDescription_Link+")["+(i+1)+"]")));
                    Assert.assertTrue(appInd.setObject(oBrowser, TaskPage.obj_EditTaskDesc_Textarea, arrTaskData[1]+appInd.getDateTime("hhmmss")));
                    Assert.assertTrue(appInd.clickObject(oBrowser, TaskPage.obj_EditTaskSave_Button));
                }

                //deadLine
                if(!arrTaskData[2].isEmpty()){
                    Assert.assertTrue(appInd.clickObject(oBrowser, TaskPage.obj_SetDeadline_Button));
                    Assert.assertTrue(appInd.clickObject(oBrowser, By.xpath("("+TaskPage.obj_DatePicker_Label+")["+(i+1)+"]")));
                }

                //Billable
                if(!arrTaskData[3].isEmpty()){
                    Assert.assertTrue(appInd.clickObject(oBrowser, TaskPage.obj_NonBillable_Button));
                    Assert.assertTrue(appInd.clickObject(oBrowser, TaskPage.obj_Billable_Link));
                }
            }
            report.writeResult(oBrowser, "Screenshot", "After entering all the required task details");
            Assert.assertTrue(appInd.clickObject(oBrowser, TaskPage.obj_CreateTask_Button));
            appInd.waitForCondition(oBrowser, By.xpath(TaskPage.obj_Task_Checkbox), "elementcount", String.valueOf(arrTaskDetails.length), 5);
            report.writeResult(oBrowser, "Screenshot", "After creating the tasks");
            List<WebElement> taskCount = oBrowser.findElements(By.xpath(TaskPage.obj_Task_Checkbox));
            Assert.assertTrue(appInd.compareExactValue(oBrowser, String.valueOf(taskCount.size()), String.valueOf(arrTaskDetails.length)));
            return true;
        }catch(Exception e){
            report.writeResult(oBrowser, "Exception", "Exception in the 'createTask()' method. "+ e);
            return false;
        }
    }


    /************************************************************************
     * Method            : deleteCustomerDetails()
     * Purpose           :
     * Parameters        :
     * Return Type       :
     * Author Name      :
     *************************************************************************/
    public boolean deleteCustomerDetails(WebDriver oBrowser){
        Actions oAction = null;
        try{
            oAction = new Actions(oBrowser);
            oAction.moveToElement(oBrowser.findElement(TaskPage.obj_DeleteCustomer_Button)).perform();
            appInd.threadSleep(2000);
            appInd.waitForCondition(oBrowser, TaskPage.obj_DeleteCustomer_Button, "clickable", "", 5);
            Assert.assertTrue(appInd.clickObject(oBrowser, TaskPage.obj_DeleteCustomer_Button));
            appInd.waitForCondition(oBrowser, TaskPage.obj_ACTIONS_Customer_Button, "clickable", "", 5);
            Assert.assertTrue(appInd.clickObject(oBrowser, TaskPage.obj_ACTIONS_Customer_Button));
            appInd.waitForCondition(oBrowser, TaskPage.obj_DeleteProject2_Button, "clickable", "", 5);
            Assert.assertTrue(appInd.clickObject(oBrowser, TaskPage.obj_DeleteProject2_Button));

            appInd.waitForCondition(oBrowser, TaskPage.Obj_DeletePermanently_Customer_Button, "clickable", "", 5);
            Assert.assertTrue(appInd.clickObject(oBrowser, TaskPage.Obj_DeletePermanently_Customer_Button));
            appInd.threadSleep(2000);
            oBrowser.navigate().refresh();
            appInd.waitForCondition(oBrowser, By.xpath(String.format(TaskPage.obj_CustomerName_Label, customerName)), "invisible", "", 5);
            Assert.assertTrue(appInd.verifyElementNotPresent(oBrowser, By.xpath(String.format(TaskPage.obj_CustomerName_Label, customerName))));
            report.writeResult(oBrowser, "Screenshot", "Customer is deleted successful");
            return true;
        }catch(Exception e){
            report.writeResult(oBrowser, "Exception", "Exception in the 'deleteCustomerDetails()' method. "+ e);
            return false;
        }
    }
}
