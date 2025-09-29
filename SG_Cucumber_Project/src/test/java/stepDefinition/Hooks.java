package stepDefinition;

import driver.CucumberTestRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;


public class Hooks extends CucumberTestRunner {
    @Before
    public void setup(Scenario scenario){
        try{
            test = extent.startTest(scenario.getName());
            oBrowser = new ChromeDriver();
            oBrowser.manage().window().maximize();
            Assert.assertNotNull(oBrowser, "oBrowser is still null");
        }catch(Exception e){
            System.out.println("Exception in 'setup()' method. " + e);
        }
    }


    @After
    public void tearDown(Scenario scenario){
        String scenarioName = null;
        TakesScreenshot ts = null;
        try{
            scenarioName = scenario.getName().replace(" ", "_");
            if(scenario.isFailed()==true){
                ts = (TakesScreenshot) oBrowser;
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenarioName);
            }
            report.endExtentReport(test);
            if(oBrowser!=null){
                oBrowser.close();
                oBrowser = null;
            }
        }catch(Exception e){
            System.out.println("Exception in 'tearDown()' method. " + e);
        }
    }
}
