// src/main/java/com/example/utils/AlertHandler.java
package utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlertHandler {

    public static int getCustomerIdFromAlert(WebDriver driver) {
        int customerId = 0;

        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customerId;
    }
}