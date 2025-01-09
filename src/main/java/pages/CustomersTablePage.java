package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

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

    //Get the created customer order in the table
    public int getTheOrderOfCreatedUser (String firstName) {
        List<WebElement> table = driver.findElements(By.xpath("//tbody//tr"));
        for(int index = 0; index < table.size(); index++) {
            WebElement element = table.get(index);
            if (element.findElement(By.tagName("td")).getText().contains(firstName)) {
                return index+1;
            }
        }
        return 0;
    }

    //Get the delete button of the created customer and click on it
    public void deleteCustomer (String firstname) {
        List<WebElement> rows = driver.findElements(By.xpath("//tbody//tr"));

        /* Delete the created User */
        for (WebElement row : rows) {
            if (row.getText().contains(firstname)) {
                row.findElement(By.xpath(".//button[text()='Delete']")).click();
                break;
            }
        }
    }

    //Search for the created customer in the table
    public boolean searchForCreatedUser (String firstName) {
        List<WebElement> table = driver.findElements(By.xpath("//tbody//tr"));
        for (WebElement element : table) {
            if (element.findElement(By.tagName("td")).getText().contains(firstName)) {
                return true;
            }
        }
        return false;
    }

}
