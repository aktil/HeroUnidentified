package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TelegramBot extends TelegramLongPollingBot {
        private static boolean isRunning = false;

        @Override
        public void onUpdateReceived(Update update) {
            if (update.hasMessage() && update.getMessage().hasText()) {
                String messageText = update.getMessage().getText();

                if (messageText.equals("/menu")) {
                    sendMenu(update.getMessage().getChatId());
                } else if (messageText.equals("/startBot")) {
                    startBot(update);
                } else if (messageText.equals("/stopBot")) {
                    stopBot(update);
                }
            }
        }

    private void startBot(Update update) {
        Main.main(null); // Вызываем метод main с аргументом null
    }


    private void sendMenu(Long chatId) {
            SendMessage message = new SendMessage();
            message.setChatId(chatId.toString());
            message.setText("Выберите действие:");

            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            keyboardMarkup.setResizeKeyboard(true);
            List<KeyboardRow> keyboard = new ArrayList<>();

            KeyboardRow row = new KeyboardRow();
            row.add("/startBot");
            row.add("/stopBot");
            keyboard.add(row);

            keyboardMarkup.setKeyboard(keyboard);
            message.setReplyMarkup(keyboardMarkup);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                System.err.println("Failed to send menu: " + e.getMessage());
            }
        }


        private void executeMainMethod(Update update) {
        try {
            // Get the main method of your class using reflection
            Class<?> clazz = Class.forName("org.example.Main");
            Method mainMethod = clazz.getMethod("main", String[].class);

            // Invoke the main method
            mainMethod.invoke(null, (Object) null);

            // Set the bot as running
            isRunning = true;
            sendResponse(update.getMessage().getChatId(), "Main method executed successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(update.getMessage().getChatId(), "Failed to execute main method: " + e.getMessage());
        }
    }

    private void stopBot(Update update) {
        if (isRunning) {
            isRunning = false;
            sendResponse(update.getMessage().getChatId(), "Bot stopped.");
        } else {
            sendResponse(update.getMessage().getChatId(), "Bot is not running.");
        }
    }

    private void sendResponse(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);

        try {
            execute(message); // Send the message
        } catch (TelegramApiException e) {
            System.err.println("Failed to send message: " + e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return "HeroUnidentifiedCleanerBot";
    }

    @Override
    public String getBotToken() {
        return "6611632677:AAHFgEpfuYtjVlLfPlu7sfY2oGHeZ7sCFGo";
    }
}
