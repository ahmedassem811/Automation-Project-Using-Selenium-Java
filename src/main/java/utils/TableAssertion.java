package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TableAssertion {

    private WebDriver driver;

    //Locators

    private By customersRow = By.xpath("//tbody//tr");

    public TableAssertion(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDataInTable(String data) {
        // Implement the logic to check if data is present in the table
        List<WebElement> rows = driver.findElements(customersRow);
        for (WebElement row : rows) {
            if (row.getText().contains(data)) {
                return true;
            }
        }
        return false;
    }
}
