package methods;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import driver.CucumberTestRunner;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import java.io.File;

public class ReportUtils extends CucumberTestRunner {
    /*************************************************************
     * Method Name          : startExtentReport
     * purpose              : It generates the extentReport object
     * params               : String htmlReportFileName
     * example              : ExtentReports extent = creteExtentReport("TestReports");
     *************************************************************/
    public ExtentReports startExtentReport(String htmlReportFileName){
        String resFilePath = null;
        File objResFilePath = null;
        File objScreenshotLoc = null;
        try{
            resFilePath = System.getProperty("user.dir")+"\\target\\extent-report";
            screenshotLoc = resFilePath + "\\screenshot";

            objResFilePath = new File(resFilePath);
            if(!objResFilePath.exists()){
                objResFilePath.mkdirs();
            }

            objScreenshotLoc = new File(screenshotLoc);
            if(!objScreenshotLoc.exists()){
                objScreenshotLoc.mkdirs();
            }

            extent = new ExtentReports(resFilePath+"\\"+htmlReportFileName+".html", false);
            extent.addSystemInfo("HostName", System.getProperty("os.name"));
            extent.addSystemInfo("UserName", System.getProperty("user.name"));
            extent.addSystemInfo("Environment", propData.get("environment"));
            extent.addSystemInfo("ApplicationName", propData.get("appName"));
            extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
            return extent;
        }catch(Exception e){
            System.out.println("Exception in 'startExtentReport()' method. "+ e);
            return null;
        }
    }


    /*************************************************************
     * Method Name          : endExtentReport
     * purpose              : It ends the extentReport object & write the result to html file
     * params               :
     * example              :
     *************************************************************/
    public void endExtentReport(ExtentTest test){
        try{
            extent.endTest(test);
            extent.flush();
        }catch(Exception e){
            System.out.println("Exception in 'endExtentReport()' method. "+ e);
        }
    }


    /*************************************************************
     * Method Name          : captureScreenshot
     * purpose              : It will capture the screenshot
     * params               :
     * example              :
     *************************************************************/
    public String captureScreenshot(WebDriver oBrowser){
        File objSrc = null;
        String destLoc = null;
        try{
            destLoc = screenshotLoc + "\\screenshot_"+appInd.getDateTime("ddmmhhmmss")+".png";
            TakesScreenshot ts = (TakesScreenshot) oBrowser;
            objSrc = ts.getScreenshotAs(OutputType.FILE);
            FileHandler.copy(objSrc, new File(destLoc));
            return destLoc;
        }catch(Exception e){
            System.out.println("Exception in 'captureScreenshot()' method. "+ e);
            return null;
        }
    }


    /*************************************************************
     * Method Name          : writeResult
     * purpose              : It will write the report to html file
     * params               :
     * example              :
     *************************************************************/
    public void writeResult(WebDriver oBrowser, String status, String message){
        try{
            switch(status.toLowerCase()){
                case "pass":
                    test.log(LogStatus.PASS, message);
                    break;
                case "fail":
                    test.log(LogStatus.FAIL, message + test.addScreenCapture(report.captureScreenshot(oBrowser)));
                    break;
                case "warning":
                    test.log(LogStatus.WARNING, message);
                    break;
                case "info":
                    test.log(LogStatus.INFO, message);
                    break;
                case "exception":
                    test.log(LogStatus.FATAL, message + test.addScreenCapture(report.captureScreenshot(oBrowser)));
                    break;
                case "screenshot":
                    test.log(LogStatus.PASS, message + test.addScreenCapture(report.captureScreenshot(oBrowser)));
                    break;
            }
        }catch(Exception e){
            System.out.println("Exceptio in 'writeResult()' method. "+e);

        }
    }
}
