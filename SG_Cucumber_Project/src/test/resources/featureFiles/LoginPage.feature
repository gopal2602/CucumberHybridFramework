Feature: Automated Login and Logout functionalities

  @Regression
  Scenario: Verify Login functionality with valid credentials and logout
    Given verify url "appUrl" is loaded successful
    Then verify login to application is successful
      | userName | password | validation |
      | admin    | manager  | positive   |
    And verify logout from application is successful


  @Regression
  Scenario: Verify Login functionality with invalid credentials
    Given verify url "appUrl" is loaded successful
    Then verify login to application is successful
      | userName | password  | validation |
      | sgUser   | Mercury@1 | negative   |