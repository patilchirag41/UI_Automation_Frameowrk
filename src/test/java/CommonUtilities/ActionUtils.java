package CommonUtilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionUtils {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public ActionUtils(WebDriver driver) {
        this.driver = BrowserLaunch.driver;
        this.wait = BrowserLaunch.wait; // Adjust the timeout as needed
        this.actions = new Actions(driver);
    }

    // Method to enter text into an input field
    public void enterText(By locator, String text) {
        WebElement element = waitForElementToBeVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    // Method to click on a button or link
    public void click(By locator) {
        WebElement element = waitForElementToBeClickable(locator);
        element.click();
    }

    // Method to hover over an element
    public void hoverOver(By locator) {
        WebElement element = waitForElementToBeVisible(locator);
        actions.moveToElement(element).perform();
    }

    // Method to select an option from a dropdown by visible text
    public void selectOptionFromDropdown(By locator, String visibleText) {
        WebElement dropdownElement = waitForElementToBeVisible(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(visibleText);
    }

    // Method to wait for an element to be visible
    public WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Method to wait for an element to be invisible
    public boolean waitForElementToBeInvisible(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // Method to wait for an element to be clickable
    public WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Method to clear text from an input field
    public void clearText(By locator) {
        WebElement element = waitForElementToBeVisible(locator);
        element.clear();
    }

    // Method to double-click on an element
    public void doubleClick(By locator) {
        WebElement element = waitForElementToBeClickable(locator);
        actions.doubleClick(element).perform();
    }

    // Method to right-click (context click) on an element
    public void rightClick(By locator) {
        WebElement element = waitForElementToBeClickable(locator);
        actions.contextClick(element).perform();
    }

    // Method to drag and drop an element from source to target
    public void dragAndDrop(By sourceLocator, By targetLocator) {
        WebElement sourceElement = waitForElementToBeVisible(sourceLocator);
        WebElement targetElement = waitForElementToBeVisible(targetLocator);
        actions.dragAndDrop(sourceElement, targetElement).perform();
    }

    // Method to perform a custom wait until a specific condition is met
    public <V> V customWaitUntilCondition(ExpectedCondition<V> condition) {
        return wait.until(condition);
    }

    // Method to check if an element is displayed
    public boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Method to check if an element is enabled
    public boolean isElementEnabled(By locator) {
        try {
            return driver.findElement(locator).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    // Method to check if an element is selected (for checkboxes/radio buttons)
    public boolean isElementSelected(By locator) {
        try {
            return driver.findElement(locator).isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    // Method to scroll to an element
    public void scrollToElement(By locator) {
        WebElement element = waitForElementToBeVisible(locator);
        actions.moveToElement(element).perform();
    }

    // Method to get the text of an element
    public String getElementText(By locator) {
        WebElement element = waitForElementToBeVisible(locator);
        return element.getText();
    }

    // Method to get the value of a specific attribute of an element
    public String getElementAttribute(By locator, String attribute) {
        WebElement element = waitForElementToBeVisible(locator);
        return element.getAttribute(attribute);
    }
}
