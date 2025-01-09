// src/main/java/com/example/utils/WindowHandler.java
package utils;

import org.openqa.selenium.WebDriver;

import java.util.Set;

public class WindowHandler {

    public static void switchToNewWindow(WebDriver driver) {
        // Get the handle of the current window
        String originalWindow = driver.getWindowHandle();

        // Loop through all window handles
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public static void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
            if (driver.getTitle().equals(title)) {
                break;
            }
        }
    }

    public static void closeCurrentWindowAndSwitchBack(WebDriver driver) {
        // Get the handle of the current window
        String currentWindow = driver.getWindowHandle();

        // Close the current window
        driver.close();

        // Switch back to the original window
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
}