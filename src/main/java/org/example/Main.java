package org.example;

import java.time.Duration;
import java.lang.System;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;




public class Main extends DOTNames  {


    public static void main(String[] args) {

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }



        String[] ids = {
                "3617842", "3487141", "3701342", "3487141", "1836881", "3487333", "3487141", "2520708",
                "3172771", "3617842", "3487333", "3844698", "4021420", "3495885", "3487333", "3299997",
                "3961778", "3021498", "4050820", "2823428", "3570890", "3987382", "3556946", "3548026",
                "2575056", "3487333", "1836881", "2568617", "3994941", "3487141", "3487333", "2520708",
                "2633428", "3172771", "3189998", "3568145", "3762963", "3617842", "3488625", "3535877",
                "2724854", "3752773", "4144328", "3669800", "4135585", "3057368", "3592709", "2563670",
                "3629358", "3487141", "384511", "2529497", "2527736", "3487141", "3487333", "3999437",
                "3569880", "3934580", "3427248", "2834390", "3758268", "3964848", "3937301", "3472706",
                "3664448", "2919717", "3830096", "4089184", "3295790", "3061158", "4096290", "3192585",
                "4144992", "3936450", "2588595", "2891084", "3943262", "4009519", "3933333", "3701342",
                "3551730", "3727859", "4005857", "3856306", "3951288", "3576510", "4124833", "3655219",
                "3657102", "3719482", "2140359", "3982352", "3838280", "2880446"

        };


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

            SendMessage message1 = new SendMessage();
            message1.setChatId("5264893430");
            message1.setText("Unidentified #" + index + ": " + name + " cleaned up");

            SendMessage message2 = new SendMessage();
            message2.setChatId("6065188925"); // Replace "YOUR_SECOND_CHAT_ID" with the second chat ID
            message2.setText("Unidentified #" + index + ": " + name + " cleaned up");


            SendMessage message3 = new SendMessage();
            message3.setChatId("1156716372"); // Replace "YOUR_SECOND_CHAT_ID" with the second chat ID
            message3.setText("Unidentified #" + index + ": " + name + " cleaned up");

            try {
                bot.execute(message1);
                bot.execute(message2);
                bot.execute(message3);
            } catch (TelegramApiException e) {
                System.err.println("Failed to send message: " + e.getMessage());
            }
        } else {
            System.out.println("No name found for ID: " + id);
        }



        // Set the path to the ChromeDriver executable

        // driver.manage().window().maximize();

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--no-sandbox");

        options.addArguments("--disable-gpu");

        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        WebDriver driver = new ChromeDriver(options);

            driver.get("https://portal.heroeld.com/service-login");

            new WebDriverWait(driver, Duration.ofSeconds(60))
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner")));

            driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("info@heroeld.com");
            driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("INFO4ELD123321!");

            driver.findElement(By.xpath("//button[contains(@class, 'ui-button') and @type='submit']")).click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[contains(@class, 'filter-column') and .//span[text()='DOT Number']]//input[@placeholder='Search']")));
            element.sendKeys(id);

            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));
            WebElement link = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ui-cell-data']/a[text()='Navigate']")));
            link.click();


            new WebDriverWait(driver, Duration.ofSeconds(60))
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner")));

            try {
                // Navigate directly to the desired page
                driver.get("https://portal.heroeld.com/portal/unidentified-events");


                // Add a wait to ensure the page is loaded
                WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(60));
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
                new WebDriverWait(driver, Duration.ofSeconds(60))
                        .until(ExpectedConditions.invisibilityOfElementLocated(By.id("hos-loader")));

                // Click on the button using JavaScript
                WebElement button = new WebDriverWait(driver, Duration.ofSeconds(60))
                        .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Manage Erroneous Data']")));

                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", button);


                try {
                    // Wait for the checkbox element to be clickable
                    WebElement checkbox = new WebDriverWait(driver, Duration.ofSeconds(60))
                            .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/div/portal/div/div/div[3]/unidentified-events-page/div[1]/div/div/content-fit/div/div/p-datatable/div/div/table/thead/tr/th[10]/span/p-checkbox/div/div[2]")));

                    // Check if the checkbox is not already selected
                    if (!checkbox.isSelected()) {
                        // Click on the checkbox
                        checkbox.click();
                    }


                    WebElement link_after_checkbox = new WebDriverWait(driver, Duration.ofSeconds(60))
                            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/div/portal/div/div/div[3]/unidentified-events-page/div[3]/a")));
                    link_after_checkbox.click();

                    try {

                        Alert alert = driver.switchTo().alert();

                        String alertText = alert.getText();
                        System.out.println("Alert text : " + alertText);

                        // Принять алерт (нажать "OK")
                        alert.accept();



                    } catch (Exception e) {
                        System.out.println("#2");

                        System.out.println("Exception occurred: alert " + e.getMessage() + " ✅");
                    }

                } catch (Exception e) {
                    System.out.println("#3");
                    System.out.println("Unidentified events :  No records found ✅" );
                }

            } catch (Exception e) {
                System.out.println("#4");
                System.out.println("Exception occurred: unidentified " + e.getMessage());
            } finally {
                try {

                    Thread.sleep(3000);
                } catch (InterruptedException e) {

                                         // Handle the exception gracefully
                    e.printStackTrace(); // Or any other appropriate handling
                }

                driver.quit();
            }
            // Close the browser


        }
    }


