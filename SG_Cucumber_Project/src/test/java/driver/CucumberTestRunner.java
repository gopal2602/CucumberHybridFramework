package driver;

import baseClass.LoginPageBaseClass;
import baseClass.TaskPageBaseClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import methods.AppIndependentMethods;
import methods.ReportUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import java.util.Map;

@CucumberOptions(
        tags = "@Regression",
        glue = {"stepDefinition"},
        features = {"src/test/resources/featureFiles"},
        plugin = {
                "pretty",
                "html:target/cucumberReport/cucumber-reports.html",
                "json:target/cucumberReport/cucumber.json"
        },
        dryRun = false, monochrome = true
)



public class CucumberTestRunner extends AbstractTestNGCucumberTests {
    public static WebDriver oBrowser;
    public static AppIndependentMethods appInd = null;
    public static LoginPageBaseClass loginMethod = null;
    public static ReportUtils report = null;
    public static TaskPageBaseClass taskMethod = null;
    public static Map<String, String> propData = null;
    public static ExtentReports extent = null;
    public static ExtentTest test = null;
    public static String screenshotLoc = null;
    public static String customerName = null;
    public static String projectName = null;

    @BeforeSuite
    public void loadClass(){
        try{
            appInd = new AppIndependentMethods();
            loginMethod = new LoginPageBaseClass();
            report = new ReportUtils();
            taskMethod = new TaskPageBaseClass();
            propData = appInd.getPropData("config");
            extent = report.startExtentReport("TestAutomationReport");
        }catch(Exception e){
            System.out.println("Exception in 'loadClass()' method. "+ e);
        }
    }

    @DataProvider
    public Object[][] scenarios() {
        return super.scenarios(); // This will provide scenarios to TestNG
    }
}
