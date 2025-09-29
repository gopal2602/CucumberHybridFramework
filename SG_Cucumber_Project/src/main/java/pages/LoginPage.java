package pages;

import org.openqa.selenium.By;

public class LoginPage {
    public static By obj_UserName_Edit = By.xpath("//input[@id='username']");
    public static By obj_Password_Edit = By.xpath("//input[@name='pwd']");
    public static By obj_Login_Button = By.xpath("//a[@id='loginButton']");
    public static By obj_SaveChange_Button = By.xpath("//input[@id='SubmitTTButton']");
    public static By obj_ShortCut_Dialog = By.xpath("//div[@style='display: block;']");
    public static By Obj_ShortCut_Close_Button = By.id("gettingStartedShortcutsMenuCloseId");
    public static By obj_HomePage_Header = By.xpath("//td[@class='pagetitle']");
    public static By obj_Logout_Link = By.xpath("//a[@id='logoutLink']");
    public static By obj_LoginPage_Header = By.id("headerContainer");
    public static By obj_Login_Logo = By.xpath("//img[contains(@src, '/timer.png')]");
    public static By obj_Login_Error_Label = By.xpath("//td[@style]/span[@class='errormsg']");
}
