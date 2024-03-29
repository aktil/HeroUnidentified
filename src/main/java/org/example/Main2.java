package org.example;

import java.time.Duration;

import org.example.DOTNames;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main2 extends DOTNames {

    public static void main(String[] args) {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Desktop\\Hero_autorefresher\\chromedriver_win32.zip");

        // Create a ChromeDriver instance
        WebDriver driver = new ChromeDriver();

        try {
            // Perform tasks with the browser window
            String[] ids = {
                    "3933333", "4009519" // IDs to check
            };

            // Counter for unidentified events
            int unidentifiedCount = 0;

            // Loop through each ID
            for (String id : ids) {
                if (idToNameMap.containsKey(id)) {
                    push(driver, id, ++unidentifiedCount);
                }
            }

            System.out.println("Total number of unidentified events done: " + unidentifiedCount);
        } finally {
            // Close the browser window
            driver.quit();
        }
    }

    public static void push(WebDriver driver, String id, int index) {
        String name = idToNameMap.get(id);

        if (name != null) {
            TelegramBot bot = new TelegramBot();

            SendMessage message = new SendMessage();
            message.setChatId("6065188925");
            message.setText("Unidentified #" + index + ": " + name + " cleaned up");

            try {
                bot.execute(message);
            } catch (TelegramApiException e) {
                System.err.println("Failed to send message: " + e.getMessage());
            }
        } else {
            System.out.println("No name found for ID: " + id);
        }

        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Desktop\\Hero_autorefresher\\chromedriver_win32.zip");

        try {
            // Create a ChromeDriver instance
            WebDriver driver = new ChromeDriver();

            // Navigate to the login page
            driver.get("https://portal.heroeld.com/service-login");

            // Wait for the login page to load
            new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner")));

            // Perform login
            driver.findElement(By.xpath("//input[@placeholder='Email']"))
                    .sendKeys("info@heroeld.com");
            driver.findElement(By.xpath("//input[@placeholder='Password']"))
                    .sendKeys("INFO4ELD123321!");
            driver.findElement(By.xpath("//button[contains(@class, 'ui-button') and @type='submit']"))
                    .click();

            // Wait for the dashboard to load
            new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.urlContains("dashboard"));

            // Your code to interact with other elements goes here...
            try {
                // Example interaction with elements
                // WebElement element = driver.findElement(By.xpath("//button[contains(text(),'Some Button')]"));
                // element.click();
                // Or you can use WebDriverWait to wait for an element and then interact with it
                // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                // WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Some Button')]")));
                // element.click();
                // Replace the above code with your actual interactions
            } catch (Exception e) {
                System.out.println("Exception occurred while interacting with elements: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        } finally {
            // Close the browser window
            driver.quit();
        }
    }
}
