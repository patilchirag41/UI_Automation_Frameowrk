Feature: Banking Automation

  Scenario: Register User on the online Banking Page
@TC001
  Scenario Outline: Successful Registration with Valid Data 
    Given I am on the Parabank registration page for <testcaseID>
    When I enter the registration details
    And I click on the "Register" button
    Then I should see a confirmation message "Your account was created successfully. You are now logged in."
    And I should be redirected to the account overview page

    Examples: 
      | testcaseID |
      | Registration_TC001      |
      #| Registration_TC002      |


  Scenario Outline: Unsuccessful Registration with Missing Required Fields
    Given I am on the Parabank registration page for <testcaseID>
    When I enter the registration details
    And I click on the "Register" button
    Then I should see an error message <errorMessage>
    And the registration should not be successful

    Examples:
      | testcaseID | errorMessage               |
      | Registration_TC003      | "Address is required."     |
      | Registration_TC004      | "Last name is required."   |
      | Registration_TC005      | "First name is required."  |
      