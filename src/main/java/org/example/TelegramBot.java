package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.Duration;

public class TelegramBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        // Handle incoming messages and commands here
        // You can access the incoming message using update.getMessage()
        // Implement your logic to respond to messages or commands

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();

            // Check if the received message contains the command to trigger execution
            if (messageText.equals("/execute")) {
                // Execute your Java code
                executeJavaCode(update);
            } else {
                // Respond with a message indicating that the command is not recognized
                sendResponse(update.getMessage().getChatId(), "Unrecognized command. Please use '/execute' to trigger execution.");
            }
        }
    }

    private void executeJavaCode(Update update) {
        // Your existing code for executing Java logic goes here
        // For example, you can call your existing method to handle unidentified events

        // Send a confirmation message after execution
        sendResponse(update.getMessage().getChatId(), "Execution completed successfully.");
    }

    private void sendResponse(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);

        try {
            execute(message); // Send the message
        } catch (TelegramApiException e) {
            // Handle the exception
            System.err.println("Failed to send message: " + e.getMessage());
        }
    }



    @Override
    public String getBotUsername() {
        // Return your bot's username
        return "HeroUnidentifiedCleanerBot";
    }

    @Override
    public String getBotToken() {
        // Return your bot's token
        return "6611632677:AAHFgEpfuYtjVlLfPlu7sfY2oGHeZ7sCFGo";
    }
}
