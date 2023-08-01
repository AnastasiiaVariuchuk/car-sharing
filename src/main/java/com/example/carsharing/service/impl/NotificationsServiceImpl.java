package com.example.carsharing.service.impl;

import com.example.carsharing.service.NotificationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@RequiredArgsConstructor
@Service
public class NotificationsServiceImpl extends TelegramLongPollingBot implements NotificationsService {
    @Value("${telegram.bot.token}")
    private String token;
    @Override
    public String getBotUsername() {
        return "CarSharingNotsBot";
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            if (message.getText().equalsIgnoreCase("/start")) {
                String chatId = message.getChatId().toString();
                String responseText = "Привет, " + message.getFrom().getFirstName() + "! Hello.";
                sendResponse(chatId, responseText);
            }
        }
    }

    public void sendResponse(String chatId, String responseText) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(responseText);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void registerBot() {
        NotificationsServiceImpl bot = new NotificationsServiceImpl();
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
