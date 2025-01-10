package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BankingPage {

    private WebDriver driver;

    //Locators
    private By bankMangerBtn = By.xpath("//button[text()='Bank Manager Login']");

    public BankingPage (WebDriver driver) {
        this.driver = driver;
    }

    //Method Perform Click on Bank manager login button.
    public void clickOnBankMangerBtn(){
        driver.findElement(bankMangerBtn).click();
    }
}
