Feature: Validate task PAge functionalities viz., Create Customer, Project and task

  @Regression
  Scenario: Verify Create and delete customer, project and task functionalities
    Given verify url "appUrl" is loaded successful
    Then verify login to application is successful
      | userName | password | validation |
      | admin    | manager  | positive   |
    When verify user navigates to task Page
    Then verify add new Customer functionality and validate the same
      | CustomerName | CustomerDesc        |
      | sg_customer  | customerDescription |
    And verify add new project functionality and validate the same
      | ProjectName | ProjectDes         |
      | sg_Project  | ProjectDescription |
    And verify create new task functionality and validate the same
      | taskDetails                                                                                            |
      | Write Test Case#Task Description#deadLine#Billable%Review Test Case#Task Description#deadLine#Billable |
    Then verify delete customer functionality and validate the same
    And verify logout from application is successful