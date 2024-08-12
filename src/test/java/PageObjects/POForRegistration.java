package PageObjects;

import org.openqa.selenium.By;

public interface POForRegistration {
    By FIRST_NAME = By.xpath("//*[@id='customer.firstName']");
    By LAST_NAME = By.xpath("//*[@id='customer.lastName']");
    By ADDRESS = By.xpath("//*[@id='customer.address.street']");
    By CITY = By.xpath("//*[@id='customer.address.city']");
    By STATE = By.xpath("//*[@id='customer.address.state']");
    By ZIP_CODE = By.xpath("//*[@id='customer.address.zipCode']");
    By PHONE_NUMBER = By.xpath("//*[@id='customer.phoneNumber']");
    By SSN = By.xpath("//*[@id='customer.ssn']");
    By USERNAME = By.xpath("//*[@id='customer.username']");
    By PASSWORD = By.xpath("//*[@id='customer.password']");
    By CONFIRM_PASSWORD = By.xpath("//*[@id='repeatedPassword']");
    By REGISTER_BUTTON = By.xpath("//input[@value='Register']");
    By CONFIRMATION_MESSAGE = By.xpath("//h1[@class='title' and contains(text(), 'Welcome')]");
    By ERROR_MESSAGE = By.xpath("//span[@class='error']");
}
