package testPack;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestScenarios {
    int customerId;
    WebDriver driver = new ChromeDriver();

    @Test
    public void createUser(){

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

        driver.navigate().to("https://www.way2automation.com/protractor-angularjs-practice-website.html");

        // Get the handle of the original window
        String originalWindow = driver.getWindowHandle();

        driver.findElement(By.xpath("//img[@alt='banking']")).click();


        // Loop through all handles and switch to the new tab
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        driver.findElement(By.xpath("//button[text()='Bank Manager Login']")).click();
        driver.findElement(By.xpath("//button[@ng-click='addCust()']")).click();

        driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Ahmed");
        driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("Assem");
        driver.findElement(By.xpath("//input[@placeholder='Post Code']")).sendKeys("02020");
        driver.findElement(By.xpath("//button[text()='Add Customer']")).click();


        // Switch to alert
        Alert alert = driver.switchTo().alert();

        // Get alert text
        String alertText = alert.getText();
        System.out.println("Alert text: " + alertText);

        // Extract customer ID using regex
        Pattern pattern = Pattern.compile("Customer added successfully with customer id :(\\d+)");
        Matcher matcher = pattern.matcher(alertText);
        if (matcher.find()) {
            customerId = Integer.parseInt(matcher.group(1));
            System.out.println("Customer ID: " + customerId);
        }

        // Close the alert
        alert.accept();

        driver.findElement(By.xpath("//button[@ng-click='showCust()']")).click();

        Assert.assertTrue(isDataInTable("Ahmed Assem 02020 Delete"));
        List<WebElement> rows = driver.findElements(By.xpath("//tbody//tr"));


        /* Delete the created User */
        for (WebElement row : rows) {
            if (row.getText().contains("Ahmed Assem 02020 Delete")) {
                row.findElement(By.xpath(".//button[text()='Delete']")).click();
                break;
            }
        }

        Assert.assertFalse(isDataInTable("Ahmed Assem 02020 Delete"));

        driver.quit();

    }

    private boolean isDataInTable(String data) {
        // Implement the logic to check if data is present in the table
        List<WebElement> rows = driver.findElements(By.xpath("//tbody//tr"));
        for (WebElement row : rows) {
            if (row.getText().contains(data)) {
                return true;
            }
        }
        return false;
    }
}