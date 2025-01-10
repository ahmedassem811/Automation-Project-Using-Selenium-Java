package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage {

    private WebDriver driver;

    //Locators
    private By BankAppBtn = By.xpath("//img[@alt='banking']");


    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    //Method Perform Click on Banking App button.
    public void clickOnBankingApp() {
        driver.findElement(BankAppBtn).click();
    }


}
