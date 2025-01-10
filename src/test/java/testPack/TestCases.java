package testPack;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utils.AlertHandler;
import utils.RandommerApiClient;
import utils.TableAssertion;
import utils.WindowHandler;

import java.io.IOException;
import java.time.Duration;

public class TestCases {

    private WebDriver driver;
    String url = "https://www.way2automation.com/protractor-angularjs-practice-website.html";
    String randomFirstName;
    String randomLastName;
    String randomPostcode;

    LandingPage landingPage;
    BankingPage bankingPage;
    AddCustomerPage addCustomer;
    TableAssertion tableAssertion;
    CustomersTablePage customersTable;

    @BeforeClass
    public void setUp() {
        // Set the path to the chromedriver executable
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }

    //Scenario 1 : As a bank manager , I can add Customer to the system.
    @Test(priority = 1,alwaysRun = true)
    public void validateUserCreatedSuccessfully() throws IOException, InterruptedException {

        // Fetch random user data from API
        randomFirstName = RandommerApiClient.fetchRandomFirstName();
        randomLastName = RandommerApiClient.fetchRandomFirstName();
        randomPostcode = RandommerApiClient.fetchRandomPostcode();

        //Creating The objects that we will use in the Test case
        landingPage = new LandingPage(driver);
        bankingPage = new BankingPage(driver);
        addCustomer = new AddCustomerPage(driver);
        tableAssertion = new TableAssertion(driver);
        customersTable = new CustomersTablePage(driver);

        //Steps of the Test Case
        driver.navigate().to(url);
        landingPage.clickOnBankingApp();
        WindowHandler.switchToNewWindow(driver);
        bankingPage.clickOnBankMangerBtn();
        addCustomer.clickAddCustomerBtn();
        addCustomer.enterFirstName(randomFirstName);
        addCustomer.enterLastName(randomLastName);   // There is no endpoint for lastname so i use the firstname endpoint
        addCustomer.enterPostalCode(randomPostcode);  // There is no endpoint for Post code so i use the Social number endpoint
        addCustomer.submitCustomerData();
        int customerId = AlertHandler.getCustomerIdFromAlert(driver);
        customersTable.clickCustomersBtn();

        //Assert that the user order in the list is the same as the value retrieved from the alert
        int orderOfTheUserInTheTable = customersTable.getTheOrderOfCreatedUser(randomFirstName);
        Assert.assertEquals(customerId,orderOfTheUserInTheTable);

        //Assert that the user details are precisely the same as entered in the form
        boolean dataInTable = tableAssertion.isDataInTable(randomFirstName+" "+randomLastName+" "+randomPostcode+" "+"Delete");
        Assert.assertTrue(dataInTable);
    }

    @Test(dependsOnMethods = "validateUserCreatedSuccessfully")
    public void deleteUser(){

        customersTable.deleteCustomer(randomFirstName);

        //Search for the created user
        boolean searchResult = customersTable.searchForCreatedUser(randomFirstName);

        //Assert that the customer is no longer found in the customers' table
        Assert.assertFalse(searchResult);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
