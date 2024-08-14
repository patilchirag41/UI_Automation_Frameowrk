package StepDefinitions;


import PageObjects.POForRegistration;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;

import com.codoid.products.fillo.Recordset;

import CommonUtilities.BrowserLaunch;
import CommonUtilities.CommonMethods;
import CommonUtilities.ExcelUtil;
import CommonUtilities.ActionUtils;

import static org.junit.Assert.*;

public class StepDefinitionforRegistration extends BrowserLaunch {
    public static Recordset rs;
    private ActionUtils actionUtils;

    @Given("I am on the Parabank registration page for (.*)")
    public void iAmOnTheParabankRegistrationPage(String testcaseID) {
        // Fetch the Recordset for the given testcaseID
        rs = ExcelUtil.getTestData(testcaseID);

        // Initialize WebDriver and navigate to the registration page
       BrowserLaunch.setBrowserToLaunch(DriverType.EDGE, false, CommonMethods.getProperty("config", "RegistrationURL"));

        // Initialize ActionUtils with the current driver instance
        actionUtils = new ActionUtils(driver);
    }

    @When("I enter the registration details")
    public void iEnterTheRegistrationDetailsForFromTheExcelSheet(String testcaseID) {
        try {
            // Move the recordset cursor to the first record (there should be only one record for the given testcaseID)
            if (rs.next()) {
                // Fetch each field from the recordset and fill in the registration form using ActionUtils
                actionUtils.enterText(POForRegistration.FIRST_NAME, rs.getField("firstName"));
                actionUtils.enterText(POForRegistration.LAST_NAME, rs.getField("lastName"));
                actionUtils.enterText(POForRegistration.ADDRESS, rs.getField("address"));
                actionUtils.enterText(POForRegistration.CITY, rs.getField("city"));
                actionUtils.enterText(POForRegistration.STATE, rs.getField("state"));
                actionUtils.enterText(POForRegistration.ZIP_CODE, rs.getField("zipCode"));
                actionUtils.enterText(POForRegistration.PHONE_NUMBER, rs.getField("phone"));
                actionUtils.enterText(POForRegistration.SSN, rs.getField("ssn"));
                actionUtils.enterText(POForRegistration.USERNAME, rs.getField("username"));
                actionUtils.enterText(POForRegistration.PASSWORD, rs.getField("password"));
                actionUtils.enterText(POForRegistration.CONFIRM_PASSWORD, rs.getField("confirmPassword"));
            } else {
                throw new RuntimeException("No data found for testcaseID: " + testcaseID);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while entering registration details: " + e.getMessage());
        }
    }

    @And("I click on the \"Register\" button")
    public void iClickOnTheRegisterButton() {
        // Use ActionUtils to click on the register button
        actionUtils.click(POForRegistration.REGISTER_BUTTON);
    }

    @Then("I should see a confirmation message {string}")
    public void iShouldSeeAConfirmationMessage(String expectedMessage) {
        // Use ActionUtils to get and verify the confirmation message text
        String actualMessage = actionUtils.getElementText(POForRegistration.CONFIRMATION_MESSAGE);
        assertEquals(expectedMessage, actualMessage);
    }

    @Then("I should be redirected to the account overview page")
    public void iShouldBeRedirectedToTheAccountOverviewPage() {
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("overview"));
    }

    @Then("I should see an error message {string}")
    public void iShouldSeeAnErrorMessage(String expectedErrorMessage) {
        // Use ActionUtils to get and verify the error message text
        String actualErrorMessage = actionUtils.getElementText(POForRegistration.ERROR_MESSAGE);
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @And("the registration should not be successful")
    public void theRegistrationShouldNotBeSuccessful() {
        String currentUrl = driver.getCurrentUrl();
        assertFalse(currentUrl.contains("overview"));
    }
}
