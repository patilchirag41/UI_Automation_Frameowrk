import PageObjects.POForRegistration;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import CommonUtilities.BrowserLaunch;
import CommonUtilities.CommonMethods;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class StepDefinitionforRegistration extends BrowserLaunch{
    
    ExcelUtil excelUtil;

    @Given("I am on the Parabank registration page")
    public void iAmOnTheParabankRegistrationPage() {
        // Initialize WebDriver and navigate to the registration page
       BrowserLaunch.setBrowserToLaunch(DriverType.EDGE,false, CommonMethods.getProperty("config","RegistrationURL"));
       
        // Initialize ExcelUtil with the path to your Excel file
        excelUtil = new ExcelUtil("path/to/your/excel/file.xlsx");
    }

    @When("I enter the registration details for {string} from the Excel sheet")
    public void iEnterTheRegistrationDetailsForFromTheExcelSheet(String testcaseID) {
        // Fetch data from Excel based on the testcaseID
        Map<String, String> testData = excelUtil.getSingleRecord(testcaseID);

        // Fill in the registration form with the data from Excel using By locators from POForRegistration
        if (testData != null) {
            driver.findElement(POForRegistration.FIRST_NAME).sendKeys(testData.get("firstName"));
            driver.findElement(POForRegistration.LAST_NAME).sendKeys(testData.get("lastName"));
            driver.findElement(POForRegistration.ADDRESS).sendKeys(testData.get("address"));
            driver.findElement(POForRegistration.CITY).sendKeys(testData.get("city"));
            driver.findElement(POForRegistration.STATE).sendKeys(testData.get("state"));
            driver.findElement(POForRegistration.ZIP_CODE).sendKeys(testData.get("zipCode"));
            driver.findElement(POForRegistration.PHONE_NUMBER).sendKeys(testData.get("phone"));
            driver.findElement(POForRegistration.SSN).sendKeys(testData.get("ssn"));
            driver.findElement(POForRegistration.USERNAME).sendKeys(testData.get("username"));
            driver.findElement(POForRegistration.PASSWORD).sendKeys(testData.get("password"));
            driver.findElement(POForRegistration.CONFIRM_PASSWORD).sendKeys(testData.get("confirmPassword"));
        } else {
            throw new RuntimeException("Test data not found for testcaseID: " + testcaseID);
        }
    }

    @And("I click on the \"Register\" button")
    public void iClickOnTheRegisterButton() {
        driver.findElement(POForRegistration.REGISTER_BUTTON).click();
    }

    @Then("I should see a confirmation message {string}")
    public void iShouldSeeAConfirmationMessage(String expectedMessage) {
        WebElement confirmationMessage = driver.findElement(POForRegistration.CONFIRMATION_MESSAGE);
        assertEquals(expectedMessage, confirmationMessage.getText());
    }

    @Then("I should be redirected to the account overview page")
    public void iShouldBeRedirectedToTheAccountOverviewPage() {
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("overview"));
    }

    @Then("I should see an error message {string}")
    public void iShouldSeeAnErrorMessage(String expectedErrorMessage) {
        WebElement errorMessage = driver.findElement(POForRegistration.ERROR_MESSAGE);
        assertEquals(expectedErrorMessage, errorMessage.getText());
    }

    @And("the registration should not be successful")
    public void theRegistrationShouldNotBeSuccessful() {
        String currentUrl = driver.getCurrentUrl();
        assertFalse(currentUrl.contains("overview"));
    }
}
