package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CustomersTablePage {

    private WebDriver driver;

    //Locators

    private By customersBtn = By.xpath("//button[@ng-click='showCust()']");

    public CustomersTablePage (WebDriver driver) {
        this.driver = driver;
    }

    public void clickCustomersBtn(){
        driver.findElement(customersBtn).click();
    }
}
