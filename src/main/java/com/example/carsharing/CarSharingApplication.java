package com.example.carsharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CarSharingApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(CarSharingApplication.class, args);
        /*registerTelegramBotNotificationService(context);*/
    }

    /*private static void registerTelegramBotNotificationService(
            ConfigurableApplicationContext context) {
        TelegramNotificationsService notificationsService =
                context.getBean(TelegramNotificationsService.class);

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(notificationsService);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }*/
}
