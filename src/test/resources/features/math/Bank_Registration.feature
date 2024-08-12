Feature: Banking Automation

  Scenario: Register User on the online Banking Page

  Scenario Outline: Successful Registration with Valid Data
    Given I am on the Parabank registration page
    When I enter the registration details for "<testcaseID>"
    And I click on the "Register" button
    Then I should see a confirmation message "Your account was created successfully. You are now logged in."
    And I should be redirected to the account overview page

    Examples: 
      | testcaseID |
      | TC001      |
      | TC002      |


  Scenario Outline: Unsuccessful Registration with Missing Required Fields
    Given I am on the Parabank registration page
    When I enter the registration details for "<testcaseID>" from the Excel sheet
    And I click on the "Register" button
    Then I should see an error message "<errorMessage>"
    And the registration should not be successful

    Examples:
      | testcaseID | errorMessage               |
      | TC003      | "Address is required."     |
      | TC004      | "Last name is required."   |
      | TC005      | "First name is required."  |
      