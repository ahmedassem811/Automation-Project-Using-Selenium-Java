// src/main/java/com/example/utils/WindowHandler.java
package utils;

import org.openqa.selenium.WebDriver;

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
}