package com.example.carsharing.service.impl;

import com.example.carsharing.model.Rental;
import com.example.carsharing.service.NotificationsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@RequiredArgsConstructor
public class TelegramNotificationsService extends TelegramLongPollingBot
        implements NotificationsService {

    @Value("${telegram.bot.token}")
    private String token;

    @Value("${telegram.chat.id}")
    private String chatId;

    @Override
    public String getBotUsername() {
        return "Car Sharing Notifications Bot";
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
                notify(responseText);
            }
        }
    }

    @Override
    public void notify(String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void newRentalParser(Rental rental) {
        String message = "New rental. User email: " + rental.getUser().getEmail()
                + ", car id: " + rental.getCar().getId()
                + ", rental date: " + rental.getRentalDate()
                + ", return date: " + rental.getReturnDate();
        notify(message);
    }

    @Override
    public void overdueRentalsNotify(List<Rental> rentalOverdueList) {
        StringBuilder builder = new StringBuilder();
        builder.append("These cars rental will be overdue in 6 hours: \n");
        rentalOverdueList
                .forEach(r -> builder.append(System.lineSeparator())
                        .append("Rental id: ").append(r.getId())
                                .append(System.lineSeparator())
                        .append("Car id: ").append(r.getCar()
                                .getId()).append(System.lineSeparator())
                        .append("Brand and model: ").append(r.getCar()
                                .getBrand()).append(" ").append(r.getCar()
                                .getModel()).append(System.lineSeparator())
                        .append("User email: ").append(r.getUser()
                                .getEmail()).append(System.lineSeparator()));
        notify(builder.toString());
    }
}
