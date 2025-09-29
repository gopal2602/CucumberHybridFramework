package methods;

import driver.CucumberTestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class AppIndependentMethods extends CucumberTestRunner {
    /**************************************************
     * Method Name      : getPropData
     * Purpose          : It will read
     * Author           :
     * Param            :
     * return Type      :
     * Ex               :
     **************************************************/
    public Map<String, String> getPropData(String fileName){
        FileInputStream fin = null;
        Properties prop = null;
        Map<String, String> data = null;
        Set<Map.Entry<Object, Object>> oSet = null;
        Iterator<Map.Entry<Object, Object>> it = null;
        try{
            data = new HashMap<String, String>();
            fin = new FileInputStream(System.getProperty("user.dir")
                    +"\\src\\main\\resources\\configuration\\"+fileName+".properties");
            prop = new Properties();
            prop.load(fin);

            oSet = prop.entrySet();
            it = oSet.iterator();
            while(it.hasNext()){
                Map.Entry<Object, Object> me = it.next();
                data.put(me.getKey().toString(), me.getValue().toString());
            }
            return data;
        }catch(Exception e){
            System.out.println("Exception in 'getPropData()' method. "+ e);
            System.out.println("change1");
            return null;
        }
        finally
        {
            try{
                fin.close();
                fin = null;
                prop = null;
                it = null;
                oSet = null;
            }catch(Exception e){}
        }
    }


    /**************************************************
     * Method Name      : getPropData
     * Purpose          : It will read
     * Author           :
     * Param            :
     * return Type      :
     * Ex               :
     **************************************************/
    public String getDateTime(String format){
        Date dt = null;
        SimpleDateFormat sdf = null;
        try{
            dt = new Date();
            sdf = new SimpleDateFormat(format);
            return sdf.format(dt);
        }catch(Exception e){
            report.writeResult(oBrowser, "Exception", "Exception in 'getDateTime()' method. "+ e);
            return null;
        }finally{
            dt = null;
            sdf = null;
        }
    }


    /**************************************************
     * Method Name      : clickObject
     * Purpose          : It will click on the WebElement
     * Author           :
     * Param            :
     * return Type      :
     * Ex               :
     **************************************************/
    public boolean clickObject(WebDriver oBrowser, By objBy){
        WebElement oEle = null;
        try{
            oEle = oBrowser.findElement(objBy);
            if(oEle.isDisplayed()){
                oEle.click();
                report.writeResult(oBrowser, "Pass", "The element '"+objBy+"' was clicked successful");
            }
            return true;
        }catch(Exception e){
            report.writeResult(oBrowser, "Exception", "Exception in 'clickObject()' method. "+ e);
            return false;
        }
    }



    /**************************************************
     * Method Name      : setObject
     * Purpose          : It will set the value to the WebElement
     * Author           :
     * Param            :
     * return Type      :
     * Ex               :
     **************************************************/
    public boolean setObject(WebDriver oBrowser, By objBy, String data){
        WebElement oEle = null;
        try{
            oEle = oBrowser.findElement(objBy);
            if(oEle.isDisplayed()){
                oEle.sendKeys(data);
                System.out.println("commiting message");
                report.writeResult(oBrowser, "Pass", "The value '"+data+"' was entered in the element '"+objBy+"' successful");
            }
            return true;
        }catch(Exception e){
            report.writeResult(oBrowser, "Exception", "Exception in 'setObject()' method. "+ e);
            return false;
        }
    }



    /**************************************************
     * Method Name      : compareExactValue
     * Purpose          :
     * Author           :
     * Param            :
     * return Type      :
     * Ex               :
     **************************************************/
    public boolean compareExactValue(WebDriver oBrowser, String actual, String expected){
        try{
            if(actual.equals(expected)){
                report.writeResult(oBrowser, "Pass", "The actual '"+actual+"' & expected '"+expected+"' values are matching");
            }else{
                report.writeResult(oBrowser, "Fail", "Mis-match In actual '"+actual+"' & expected '"+expected+"' values");
                return false;
            }
            return true;
        }catch(Exception e){
            report.writeResult(oBrowser, "Exception", "Exception in 'compareExactValue()' method. "+ e);
            return false;
        }
    }


    /**************************************************
     * Method Name      : comparePartialValue
     * Purpose          :
     * Author           :
     * Param            :
     **************************************************/
    public boolean comparePartialValue(WebDriver oBrowser, String actual, String expected){
        try{
            if(actual.contains(expected)){
                report.writeResult(oBrowser, "Pass", "The actual '"+actual+"' & expected '"+expected+"' values are matching");
            }else{
                report.writeResult(oBrowser, "Fail", "Mis-match In actual '"+actual+"' & expected '"+expected+"' values");
                return false;
            }
            return true;
        }catch(Exception e){
            report.writeResult(oBrowser, "Exception", "Exception in 'comparePartialValue()' method. "+ e);
            return false;
        }
    }


    /**************************************************
     * Method Name      : verifyText
     * Purpose          : to verify the tet from the element
     * Author           :
     * Param            :
     **************************************************/
    public boolean verifyText(WebDriver oBrowser, By objBy, String objType, String expected){
        String actual = null;
        WebElement oEle = null;
        String arrObjectType[];
        Select oSelect = null;
        try{
            arrObjectType = objType.split("#", -1);
            oEle = oBrowser.findElement(objBy);
            if(oEle.isDisplayed()){
                switch(arrObjectType[0].toLowerCase()){
                    case "text":
                        actual = oEle.getText();
                        break;
                    case "attribute":
                        actual = oEle.getAttribute(arrObjectType[1]);
                        break;
                    case "dropdown":
                        oSelect = new Select(oEle);
                        actual = oSelect.getFirstSelectedOption().getText();
                        break;
                    default:
                        report.writeResult(oBrowser, "Fail", "Invalid object type '"+objType+"' was specified");
                        return false;
                }

                Assert.assertTrue(compareExactValue(oBrowser, actual, expected));
            }
            return true;
        }catch(Exception e){
            report.writeResult(oBrowser, "Exception", "Exception in 'verifyText()' method. "+ e);
            return false;
        }
    }


    /**************************************************
     * Method Name      : verifyElementPresent
     * Purpose          :
     * Author           :
     * Param            :
     **************************************************/
    public boolean verifyElementPresent(WebDriver oBrowser, By objBy){
        List<WebElement> oEles = null;
        try{
            oEles = oBrowser.findElements(objBy);
            if(oEles.size() > 0){
                report.writeResult(oBrowser, "Pass", "The element '"+objBy+"' present in the DOM");
                return true;
            }else{
                report.writeResult(oBrowser, "Fail", "Failed to find the element '"+objBy+"' in the DOM");
                return false;
            }
        }catch(Exception e){
            report.writeResult(oBrowser, "Exception", "Exception in 'verifyElementPresent()' method. "+ e);
            return false;
        }
    }


    /**************************************************
     * Method Name      : verifyElementNotPresent
     * Purpose          :
     * Author           :
     * Param            :
     **************************************************/
    public boolean verifyElementNotPresent(WebDriver oBrowser, By objBy){
        List<WebElement> oEles = null;
        try{
            oEles = oBrowser.findElements(objBy);
            if(oEles.size() > 0){
                report.writeResult(oBrowser, "Fail", "The element '"+objBy+"' still exist in the DOM");
                return false;
            }else{
                report.writeResult(oBrowser, "Pass", "The element '"+objBy+"' was removed from the DOM");
                return true;
            }
        }catch(Exception e){
            report.writeResult(oBrowser, "Exception", "Exception in 'verifyElementNotPresent()' method. "+ e);
            return false;
        }
    }


    /**************************************************
     * Method Name      : verifyOptionalElementPresent
     * Purpose          :
     * Author           :
     * Param            :
     **************************************************/
    public boolean verifyOptionalElementPresent(WebDriver oBrowser, By objBy){
        List<WebElement> oEles = null;
        try{
            oEles = oBrowser.findElements(objBy);
            if(oEles.size() > 0){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            report.writeResult(oBrowser, "Exception", "Exception in 'verifyOptionalElementPresent()' method. "+ e);
            return false;
        }
    }


    /**************************************************
     * Method Name      : waitForCondition
     * Purpose          :
     * Author           :
     * Param            :
     **************************************************/
    public boolean waitForCondition(WebDriver oBrowser, By objBy, String waitCondition, String text, int waitTimeout){
        WebDriverWait oWait = null;
        try{
            oWait = new WebDriverWait(oBrowser, Duration.ofSeconds(waitTimeout));
            switch(waitCondition.toLowerCase()){
                case "clickable":
                    oWait.until(ExpectedConditions.elementToBeClickable(objBy));
                    break;
                case "visible":
                    oWait.until(ExpectedConditions.presenceOfElementLocated(objBy));
                    break;
                case "invisible":
                    oWait.until(ExpectedConditions.invisibilityOfElementLocated(objBy));
                    break;
                case "text":
                    oWait.until(ExpectedConditions.textToBePresentInElementLocated(objBy, text));
                    break;
                case "elementcount":
                    oWait.until(ExpectedConditions.numberOfElementsToBe(objBy, Integer.parseInt(text)));
                    break;
                default:
                    report.writeResult(oBrowser, "Fail", "Invalid wait Condition '"+waitCondition+"' is provided");
                    return false;
            }
            return true;
        }catch(Exception e){
            System.out.println("Exception in 'waitForCondition()' method. "+ e);
            return false;
        }
    }



    /************************************************************************
     * Method            : launchBrowser()
     * Purpose           : This method launches the required browsers viz., chrome, firefox and edge
     * Parameters        : String browserName
     * Return Type       : WebDriver
     * Author Name      : Test user1
     * Reviewed By      :
     * Reviewed Date    :
     * Modified by      :
     *************************************************************************/
    public WebDriver launchBrowser(String browserName){
        WebDriver oDriver = null;
        try{
            switch(browserName.toLowerCase()){
                case "chrome":
                    oDriver = new ChromeDriver();
                    break;
                case "firefox":
                    oDriver = new FirefoxDriver();
                    break;
                case "edge":
                    oDriver = new EdgeDriver();
                    break;
                default:
                    report.writeResult(oDriver, "Fail", "Invalid browser name '"+browserName+"' was mentioned. Please provide the valid browser name");
                    return null;
            }

            if(oDriver!=null){
                oDriver.manage().window().maximize();
                report.writeResult(oDriver, "Pass", "The '"+browserName+"' browser was launched successful");
                return oDriver;
            }else{
                report.writeResult(oDriver, "Pass", "Failed to launch the '"+browserName+"' browser");
                return null;
            }
        }catch(Exception e){
            report.writeResult(oDriver, "Exception", "Exception in the 'launchBrowser()' method. "+ e);
            return null;
        }
    }


    /**************************************************
     * Method Name      : threadSleep
     * Purpose          :
     * Author           :
     * Param            :
     **************************************************/
    public void threadSleep(int waitTimeout){
        try{
            Thread.sleep(waitTimeout);
        }catch(Exception e){}
    }
}
