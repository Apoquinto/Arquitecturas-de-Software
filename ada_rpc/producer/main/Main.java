package main;

import client.Client;
import rabbitmq.RabbitMQModule;

public class Main {
    private static final String message = "1";

    public static void main(String[] args) {
        Client sender = new RabbitMQModule();
        System.out.println(sender.sendRequest(message));
    }
}