package testPack;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.AlertHandler;
import utils.RandommerApiClient;
import utils.TableAssertion;
import utils.WindowHandler;

import java.io.IOException;
import java.time.Duration;

public class TestCases {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set the path to the chromedriver executable
        driver = new ChromeDriver();
        driver.navigate().to("https://www.way2automation.com/protractor-angularjs-practice-website.html\");\n");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }


    @Test
    public void validateUserCreatedSuccessfully() throws IOException, InterruptedException {
        // Fetch random user data from API
        String randomFirstName = RandommerApiClient.fetchRandomFirstName();
        String randomLastName = RandommerApiClient.fetchRandomFirstName();
        String randomPostcode = RandommerApiClient.fetchRandomPostcode();



        LandingPage landingPage = new LandingPage(driver);
        BankingPage bankingPage = new BankingPage(driver);
        AddCustomerPage addCustomer = new AddCustomerPage(driver);
        TableAssertion tableAssertion = new TableAssertion(driver);
        CustomersTablePage customersTable = new CustomersTablePage(driver);


        landingPage.clickOnBankingApp();
        WindowHandler.switchToNewWindow(driver);
        bankingPage.clickOnBankMangerBtn();
        addCustomer.clickAddCustomerBtn();
        addCustomer.enterFirstName(randomFirstName);
        addCustomer.enterLastName(randomLastName);
        addCustomer.enterPostalCode(randomPostcode);
        addCustomer.submitCustomerData();

        AlertHandler.getCustomerIdFromAlert(driver);

        customersTable.clickCustomersBtn();
      //  System.out.println(AlertHandler.getCustomerIdFromAlert(driver));
        boolean dataInTable = tableAssertion.isDataInTable(randomFirstName+" "+randomLastName+" "+randomPostcode+" "+"Delete");
        Assert.assertTrue(dataInTable);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
