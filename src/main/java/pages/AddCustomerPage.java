package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddCustomerPage {


    private WebDriver driver;

    //Locators
    private By addCustomerBtn = By.xpath("//button[@ng-click='addCust()']");
    private By firstName = By.xpath("//input[@placeholder='First Name']");
    private By lastName = By.xpath("//input[@placeholder='Last Name']");
    private By postCode = By.xpath("//input[@placeholder='Post Code']");
    private By submitBtn = By.xpath("//button[text()='Add Customer']");

    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAddCustomerBtn(){
        driver.findElement(addCustomerBtn).click();
    }


    public void enterFirstName(String fName){
        driver.findElement(firstName).sendKeys(fName);
    }

    public void enterLastName(String lName){
        driver.findElement(lastName).sendKeys(lName);
    }

    public void enterPostalCode(String code){
        driver.findElement(postCode).sendKeys(code);
    }

    public void submitCustomerData(){
        driver.findElement(submitBtn).click();
    }





}
