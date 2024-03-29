package org.example;

import java.time.Duration;
import java.lang.System;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;



public class Main extends DOTNames  {

    // Map to associate IDs with names

    WebDriver driver = new ChromeDriver();


    public static void main(String[] args) {


        String[] ids = {
                "3487141", "3487333", "3487141", "2520708", "3172771",
                 "3844698",  "4021420", "3495885", "3487333",
                "3299997", "3961778", "3940221", "3021498", "4050820",
                "2823428", "3570890", "3987382", "3556946", "3548026",
                "2575056", "3539665", "1836881", "2568617", "3994941",
                "3487141", "3487333", "2520708", "2633428", "3172771",
                "3189998", "3568145",
                "3762963", "3617842", "3488625", "3535877", "2724854",
                "3752773", "4144328", "3669800", "4135585", "3057368",
                "3592709", "2563670", "3629358", "3860519", "384511",
                "2529497", "2527736", "3999437", "3569880", "3934580",
                "3427248", "2834390", "3758268", "3964848", "3937301",
                "3472706", "3664448", "2919717", "3830096", "4089184",
                "3295790", "3061158", "4096290", "3192585", "4144992",
                "3936450", "2588595", "2891084", "3943262", "4009519",
                "3933333", "3701342", "3551730", "3727859", "4005857",
                "3856306", "3951288", "3576510", "4124833", "3655219",
                "3657102", "3719482", "2140359", "3982352", "3838280",
                "2880446"
        };

        //TODO

        // need to check this company : "3933333" and "4009519"
        // ZZZZZZAlmaz Logistics LLC



        int unidentifiedCount = 0; // Counter for unidentified events

        for (String id : ids) {
            if (idToNameMap.containsKey(id)) {
                push(id, ++unidentifiedCount);
            }
        }

        System.out.println("Total number of unidentified events done: " + unidentifiedCount);
    }






    public static void push(String id, int index) {
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
        System.setProperty("webdiver.chrome.driver", "C:\\Users\\User\\Desktop\\Hero_autorefresher\\chromedriver_win32.zip");

        // driver.manage().window().maximize();
        WebDriver driver = new ChromeDriver();

        driver.get("https://portal.heroeld.com/service-login");

        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner")));

        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("info@heroeld.com");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("INFO4ELD123321!");

        driver.findElement(By.xpath("//button[contains(@class, 'ui-button') and @type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[contains(@class, 'filter-column') and .//span[text()='DOT Number']]//input[@placeholder='Search']")));
        element.sendKeys(id);

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement link = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ui-cell-data']/a[text()='Navigate']")));
        link.click();


        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner")));

        try {
            // Navigate directly to the desired page
            driver.get("https://portal.heroeld.com/portal/unidentified-events");


            // Add a wait to ensure the page is loaded
            WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait3.until(ExpectedConditions.urlContains("unidentified-events"));
            WebElement link2 = driver.findElement(By.xpath("//a[@href='/portal/unidentified-events']"));


            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", link2);

        } catch (Exception e) {
            System.out.println("#1");
            System.out.println("Exception occurred: " + e.getMessage());
        }

        try {
            // Wait for the covering element to disappear
            new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.id("hos-loader")));

            // Click on the button using JavaScript
            WebElement button = new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Manage Erroneous Data']")));

            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", button);

            try {
                // Wait for the checkbox element to be clickable
                WebElement checkbox = new WebDriverWait(driver, Duration.ofSeconds(30))
                        .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/div/portal/div/div/div[3]/unidentified-events-page/div[1]/div/div/content-fit/div/div/p-datatable/div/div/table/thead/tr/th[10]/span/p-checkbox/div/div[2]")));

                // Check if the checkbox is not already selected
                if (!checkbox.isSelected()) {
                    // Click on the checkbox
                    checkbox.click();
                }


                WebElement link_after_checkbox = new WebDriverWait(driver, Duration.ofSeconds(30))
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/div/portal/div/div/div[3]/unidentified-events-page/div[3]/a")));
                link_after_checkbox.click();

                try {

                    Alert alert = driver.switchTo().alert();

                    String alertText = alert.getText();
                    System.out.println("Alert text: " + alertText);

                    // Принять алерт (нажать "OK")
                    alert.accept();

                } catch (Exception e) {
                    System.out.println("#2");

                    System.out.println("Exception occurred: alert " + e.getMessage());
                }

            } catch (Exception e) {
                System.out.println("#3");
                System.out.println("Exception occurred: checkbox " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("#4");
            System.out.println("Exception occurred: unidentified " + e.getMessage());
        }
        // Close the browser


    }

}
