package pages;

import org.openqa.selenium.By;

public class TaskPage {
    public static By obj_TASKS_Menu = By.xpath("//div[text()='TASKS']");
    public static By obj_TaskPage_Header = By.xpath("//div[text()='Customers & Projects']");
    public static By obj_AddNew_Button = By.xpath("//div[text()='Add New']");
    public static By obj_NewCustomer_Button = By.xpath("//div[text()='+ New Customer']");
    public static By obj_EnterCustomerName_Edit = By.xpath("//div[@id]/input[@placeholder='Enter Customer Name']");
    public static By obj_AddCustomerDesc_TextArea = By.xpath("//textarea[@id='customerLightBox_descriptionField']");
    public static By obj_CreateCustomer_Button = By.xpath("//span[text()='Create Customer']");
    public static String obj_CustomerName_Label = "//div[text()='%s']";
    public static By obj_NewProject_Button = By.xpath("//div[text()='+ New Project']");
    public static By obj_EnterProjectName_Edit = By.xpath("//input[@id='projectPopup_projectNameField']");
    public static By obj_Customer_Dropdown = By.xpath("//div[@id='projectPopup_customerContainer']//button");
    public static String obj_Customer_Dropdown_Item = "//ul/li/a[text()='%s']";
    public static By obj_AddProjectDescription_TextArea = By.xpath("//textArea[@name='projectDescriptionField']");
    public static By obj_CreateProject_Button = By.xpath("//span[text()='Create Project']");
    public static String obj_ProjectName_Label = "//div[text()='%s']";
    public static By obj_AddNewTask_Button = By.xpath("//div[text()='Add New Task']");
    public static By obj_CreateNewTask_Button = By.xpath("//div[text()='Create new tasks']");
    public static By obj_CustomerName_Dropdown = By.xpath("//div[@id='createTasksPopup_customerSelector']//button");
    public static By obj_ProjectName_Dropdown = By.xpath("//div[@id='createTasksPopup_projectSelector']//button");
    public static String obj_customerProject_Label = "//div[contains(@class, 'customerProjectListSelector')]/ul/li/a[text()='%s']";
    public static String obj_TaskName_Edit = "//td[@class='nameCell first']/input";
    public static String obj_TaskDescription_Link = "//a[@id='descriptionElement']";
    public static By obj_EditTaskDesc_Textarea = By.xpath("//textarea[@name='comment']");
    public static By obj_EditTaskSave_Button = By.xpath("//input[@id='scbutton']");
    public static By obj_SetDeadline_Button = By.xpath("//button[text()='set deadline']");
    public static String obj_DatePicker_Label = "//td[contains(@class, 'x-date-selected')]";
    public static By obj_NonBillable_Button = By.xpath("//button[text()='Non-Billable']");
    public static By obj_Billable_Link = By.xpath("//div[contains(@style,'visible;')]/ul/li/a[text()='Billable']");
    public static By obj_CreateTask_Button = By.xpath("//span[text()='Create Tasks']");
    public static String obj_Task_Checkbox = "//tr[contains(@class, 'taskRow')]/td[@class='selection']";
    public static String obj_TaskName_Label = "//tr[contains(@class, 'taskRow')]/td[@class='names']//div[starts-with(@class, 'title')]";
    public static By obj_TaskHeader_Checkbox = By.xpath("//table[@class='headerRowTable']//td[@class='selection']");
    public static By obj_Delete_Button = By.xpath("//div[@class='deleteButton']");
    public static By obj_DeltePermanently_Button = By.xpath("//span[text()='Delete permanently']");
    public static String obj_ProjectNameContainer_Label = "//div[text()='%s']";
    public static By obj_DeleteProject_Button = By.xpath("//div[contains(@class, 'projectNode')]/div[@class='editButton available']");
    public static By obj_DeleteCustomer_Button = By.xpath("//div[contains(@class, 'customerNode')]/div[@class='editButton available']");
    public static By obj_ACTIONS_Project_Button = By.xpath("//div[@class='projectNamePlaceholder']/following-sibling::div//div[text()='ACTIONS']");
    public static By obj_ACTIONS_Customer_Button = By.xpath("//div[@class='customerNamePlaceHolder']/following-sibling::div//div[text()='ACTIONS']");
    public static By obj_DeleteProject2_Button = By.xpath("//div[@class='deleteButton' and contains(@style, 'inline-block;')]/div[text()='Delete']");
    public static By obj_DeletePermanently_project_Button = By.xpath("//span[@id='projectPanel_deleteConfirm_submitTitle']");
    public static By Obj_DeletePermanently_Customer_Button = By.xpath("//span[@id='customerPanel_deleteConfirm_submitTitle']");








}
