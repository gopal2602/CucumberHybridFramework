package baseClass;

import driver.CucumberTestRunner;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.LoginPage;

import java.util.List;
import java.util.Map;

public class LoginPageBaseClass extends CucumberTestRunner {
    /************************************************************************
     * Method            : navigateURL()
     * Purpose           : This method loads the url
     * Parameters        : WebDriver oBrowser, String strURL
     * Return Type       : boolean
     * Author Name      : Test user
     *************************************************************************/
    public boolean navigateURL(WebDriver oBrowser, String strURL){
        try{
            oBrowser.navigate().to(strURL);
            appInd.waitForCondition(oBrowser, LoginPage.obj_Login_Button, "Clickable", "", 5);
            Assert.assertTrue(appInd.compareExactValue(oBrowser, oBrowser.getTitle(), "actiTIME - Login"));
            report.writeResult(oBrowser, "Screenshot", "The url '"+strURL+"' was loaded successful");
            return true;
        }catch(Exception e){
            System.out.println("Exception in the 'navigateURL()' method. "+ e);
            return false;
        }
    }


    /**************************************************
     * Method Name      : verifyLoginLogo
     * Purpose          :
     * Author           :
     * Param            :
     **************************************************/
    public boolean verifyLoginLogo(WebDriver oBrowser, By objBy){
        try{
            Assert.assertTrue(appInd.verifyElementPresent(oBrowser, objBy));
            return true;
        }catch(Exception e){
            report.writeResult(oBrowser, "Exception", "Exception in 'verifyLoginLogo()' method. "+ e);
            return false;
        }
    }


    /**************************************************
     * Method Name      : loginToApplication
     * Purpose          :
     * Author           :
     * Param            :
     **************************************************/
    public boolean loginToApplication(WebDriver oBrowser, DataTable dataTable){
        List<Map<String, String>> inputData = null;
        try{
            inputData = dataTable.asMaps(String.class, String.class);
            Assert.assertTrue(appInd.setObject(oBrowser, LoginPage.obj_UserName_Edit, inputData.get(0).get("userName")));
            Assert.assertTrue(appInd.setObject(oBrowser, LoginPage.obj_Password_Edit, inputData.get(0).get("password")));
            Assert.assertTrue(appInd.clickObject(oBrowser, LoginPage.obj_Login_Button));
            if(inputData.get(0).get("validation").equalsIgnoreCase("Positive")){
                appInd.waitForCondition(oBrowser, LoginPage.obj_SaveChange_Button, "Clickable", "", 5);
                Assert.assertTrue(appInd.verifyText(oBrowser, LoginPage.obj_HomePage_Header, "Text", "Enter Time-Track"));
                if(appInd.verifyOptionalElementPresent(oBrowser, LoginPage.obj_ShortCut_Dialog)){
                    Assert.assertTrue(appInd.clickObject(oBrowser, LoginPage.Obj_ShortCut_Close_Button));
                }
                report.writeResult(oBrowser, "Screenshot", "Login to actiTime was successful");
            }else if(inputData.get(0).get("validation").equalsIgnoreCase("Negative")){
                appInd.waitForCondition(oBrowser, LoginPage.obj_Login_Error_Label, "Clickable", "", 5);
                Assert.assertTrue(appInd.verifyText(oBrowser, LoginPage.obj_Login_Error_Label, "Text", "Username or Password is invalid. Please try again."));
                report.writeResult(oBrowser, "Screenshot", "Unable to login as the credentials and Invalid");
            }
            return true;
        }catch(Exception e){
            report.writeResult(oBrowser, "Exception", "Exception in 'loginToApplication()' method. "+ e);
            return false;
        }
    }


    /**************************************************
     * Method Name      : loginToApplication
     * Purpose          :
     * Author           :
     * Param            :
     **************************************************/
    public boolean logoutFromApplication(WebDriver oBrowser){
        try{
            appInd.waitForCondition(oBrowser, LoginPage.obj_Logout_Link, "Clickable", "", 5);
            Assert.assertTrue(appInd.clickObject(oBrowser, LoginPage.obj_Logout_Link));
            appInd.waitForCondition(oBrowser, LoginPage.obj_LoginPage_Header, "Text", "Please identify yourself", 10);
            Assert.assertTrue(appInd.verifyText(oBrowser, LoginPage.obj_LoginPage_Header, "Text", "Please identify yourself"));
            report.writeResult(oBrowser, "Screenshot", "Logout from the application was successful");
            return true;
        }catch(Exception e){
            report.writeResult(oBrowser, "Exception", "Exception in 'loginToApplication()' method. "+ e);
            return false;
        }
    }
}
